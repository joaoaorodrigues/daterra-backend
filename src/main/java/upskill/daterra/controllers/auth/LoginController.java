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
        // Create authentication token
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginModel.getEmail(),
                loginModel.getPassword()
        );

        // Authenticate (using UserAuthenticationProvider)
        Authentication authenticated = authenticationManager.authenticate(authentication);

        // Set security context
        SecurityContextHolder.getContext().setAuthentication(authenticated);

        System.out.println("SecurityContext after login: " +
                SecurityContextHolder.getContext().getAuthentication());

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        System.out.println("Created session ID: " + session.getId());
        System.out.println("Stored SecurityContext: " + SecurityContextHolder.getContext());

        // Get the authenticated user
        User user = (User) authenticated.getPrincipal();
        String userType = user.getClass().getSimpleName().toUpperCase();

        // Build response
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