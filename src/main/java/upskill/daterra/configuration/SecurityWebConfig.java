package upskill.daterra.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityWebConfig {
    @Autowired
    UserAuthenticationProvider userAuthenticationProvider;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrfConfigurer -> {
            csrfConfigurer.disable();
        });
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/login", "/signup").permitAll();
            auth.requestMatchers("/user", "/user/**").authenticated();
            auth.requestMatchers("/novaTarefa").authenticated();
            auth.requestMatchers("/countries").hasRole("ADMIN");
            auth.requestMatchers("**").denyAll();
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
}