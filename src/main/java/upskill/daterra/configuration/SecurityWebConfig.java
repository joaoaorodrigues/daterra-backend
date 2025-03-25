package upskill.daterra.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityWebConfig {

    private final UserAuthenticationProvider userAuthenticationProvider;

    // Constructor injection for UserAuthenticationProvider
    @Autowired
    public SecurityWebConfig(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // PasswordEncoder bean definition
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrfConfigurer -> {
            csrfConfigurer.disable();
        });
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/login", "/signup").permitAll();
            auth.requestMatchers("/user", "/user/**").authenticated();
            auth.requestMatchers("/novaTarefa").authenticated();
            auth.requestMatchers("/countries").hasRole("ADMIN");
            auth.requestMatchers("/public/**").permitAll();
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
        httpSecurity.authenticationProvider(userAuthenticationProvider); // Injecting UserAuthenticationProvider
        return httpSecurity.build();
    }
}
