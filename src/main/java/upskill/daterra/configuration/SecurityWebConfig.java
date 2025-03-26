package upskill.daterra.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityWebConfig {

    private final UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    public SecurityWebConfig(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrfConfigurer -> {
            csrfConfigurer.disable();
        });
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/**").permitAll();

//            auth.requestMatchers("/", "/criar-conta/**", "/login", "/mapa", "/contactos", "/ajuda").permitAll();
//            auth.requestMatchers("/consumidor/**").hasRole("CONSUMIDOR");
//            auth.requestMatchers("/produtor/**").hasRole("PRODUTOR");
//            auth.requestMatchers("/admin/**").hasRole("ADMIN");
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