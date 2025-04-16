package upskill.daterra.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityWebConfig {

    private final UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    public SecurityWebConfig(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(userAuthenticationProvider)
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**", "/produtores/**", "/teste/**", "/session-check").permitAll()
                                .requestMatchers("/consumidor/**").hasAnyRole("CONSUMIDOR")
                                .requestMatchers("/**").permitAll()
//                        .requestMatchers("/admin/", "/admin/**").hasAuthority("ROLE_ADMIN")
//                        .requestMatchers("/produtor/**").hasAuthority("ROLE_PRODUTOR")
//                        .requestMatchers("/cliente/**").hasAuthority("ROLE_CONSUMIDOR")
//                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpStatus.OK.value());
                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            System.out.println("Unauthorized access attempt: " + request.getRequestURI());
                            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                        })
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        ;

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow the front-end application from localhost:5173
        configuration.setAllowedOrigins(
                Collections.singletonList("http://localhost:5173")
        );

        // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Allow the necessary headers (adjust according to your needs)
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "X-Requested-With", "Accept"));

        configuration.setExposedHeaders(Arrays.asList("Set-Cookie"));

        configuration.setMaxAge(3600L);

        // Allow credentials (cookies, etc.)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public FilterRegistrationBean<Filter> securityContextLoggingFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpSession session = httpRequest.getSession(false);

                if (session != null) {
                    Object securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
                    System.out.println("SPRING_SECURITY_CONTEXT in session: " + securityContext);
                } else {
                    System.out.println("No session found.");
                }

                chain.doFilter(request, response);
            }
        });

        filterRegistrationBean.addUrlPatterns("/*"); // Apply to all endpoints
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); // Execute first in the chain
        return filterRegistrationBean;
    }


}