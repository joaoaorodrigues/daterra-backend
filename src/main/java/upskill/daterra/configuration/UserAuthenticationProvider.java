package upskill.daterra.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import upskill.daterra.entities.Admin;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.entities.User;
import upskill.daterra.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserAuthenticationProvider(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        System.out.println("AUTH ATTEMPT FOR: " + email);
        String password = authentication.getCredentials().toString();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("AUTH ATTEMPT FOR: " + email);
                    return new BadCredentialsException("Invalid username or password");
                });

        System.out.println("Found user type: " + user.getClass().getSimpleName());

        if (passwordEncoder.matches(password, user.getPassword())) {
            List<GrantedAuthority> roleList = new ArrayList<>();

            switch (user) {
                case Admin admin -> {
                    System.out.println("Granting ROLE_ADMIN to " + email);
                    roleList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                }
                case Consumidor consumidor -> roleList.add(new SimpleGrantedAuthority("ROLE_CONSUMIDOR"));
                case Produtor produtor -> roleList.add(new SimpleGrantedAuthority("ROLE_PRODUTOR"));
                default -> {
                }
            }

            return new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    roleList
            );
        }

        throw new BadCredentialsException("Invalid username or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
