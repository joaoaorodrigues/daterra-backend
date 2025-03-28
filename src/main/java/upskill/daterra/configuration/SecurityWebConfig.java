package upskill.daterra.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(cors -> {
            cors.configurationSource(corsConfigurationSource());
        });
        httpSecurity.csrf(csrfConfigurer -> {
            csrfConfigurer.disable();
        });
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/**").permitAll();

        });
        httpSecurity.formLogin(loginConfig -> {
            loginConfig.failureHandler((request, response, exception) -> {
                response.setStatus(401);
            });
            loginConfig.successHandler((request, response, exception) -> {
                response.setStatus(200);
            });
            loginConfig.usernameParameter("email");
            loginConfig.loginProcessingUrl("/login");
        });
        httpSecurity.authenticationProvider(userAuthenticationProvider);
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow the front-end application from localhost:5173
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));

        // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Allow the necessary headers (adjust according to your needs)
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "X-Requested-With", "Accept"));

        // Allow credentials (cookies, etc.)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}