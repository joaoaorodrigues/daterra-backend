package upskill.daterra.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import upskill.daterra.entities.User;
import upskill.daterra.services.produto.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        User user = userService.loginUser(email, password);
        if(user != null) {
            List<GrantedAuthority> roleList = new ArrayList<>();
            roleList.add(new SimpleGrantedAuthority("ROLE_USER"));
//            if(user.isAdmin()) {
//                roleList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//            }
            return new UsernamePasswordAuthenticationToken(email, password, roleList);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}