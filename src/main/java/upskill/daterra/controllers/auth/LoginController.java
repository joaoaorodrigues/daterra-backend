package upskill.daterra.controllers.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Admin;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.entities.User;
import upskill.daterra.models.LoginModel;
import upskill.daterra.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public LoginController(UserRepository userRepository,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginModel loginModel,
                                                     HttpServletRequest request) {
        // 1. Create authentication token
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginModel.getEmail(),
                loginModel.getPassword()
        );

        // 2. Authenticate (this uses your UserAuthenticationProvider)
        Authentication authenticated = authenticationManager.authenticate(authentication);

        // 3. Set security context
        SecurityContextHolder.getContext().setAuthentication(authenticated);

        System.out.println("SecurityContext after login: " +
                SecurityContextHolder.getContext().getAuthentication());

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        System.out.println("Created session ID: " + session.getId());
        System.out.println("Stored SecurityContext: " + SecurityContextHolder.getContext());

        // 4. Get the already-authenticated user
        User user = (User) authenticated.getPrincipal();

        // 5. Determine user type
        String userType;
        if (user instanceof Admin) {
            userType = "ADMIN";
        } else if (user instanceof Produtor) {
            userType = "PRODUTOR";
        } else if (user instanceof Consumidor) {
            userType = "CONSUMIDOR";
        } else {
            throw new IllegalStateException("Unknown user type");
        }

        // 6. Build response
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("nome", user.getFirstName());
        responseBody.put("email", loginModel.getEmail());
        responseBody.put("tipoUtilizador", userType.toLowerCase());

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/session-check")
    public ResponseEntity<?> checkSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Active session auth: " + auth);
        return ResponseEntity.ok(auth);
    }

}