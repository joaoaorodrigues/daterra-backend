package upskill.daterra.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.User;
import upskill.daterra.models.LoginModel;
import upskill.daterra.repositories.UserRepository;
import upskill.daterra.services.auth.LoginService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private final LoginService loginService;
    private final UserRepository userRepository;

    @Autowired
    public LoginController(LoginService loginService,
                           UserRepository userRepository) {
        this.loginService = loginService;
        this.userRepository=userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginModel loginModel) {

        String userType = loginService.authenticateUser(loginModel);
        User user = userRepository.findByEmail(loginModel.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("nome", user.getFirstName());
        responseBody.put("email", loginModel.getEmail());
        responseBody.put("tipoUtilizador", userType.toLowerCase());

        // Return the response
        return ResponseEntity.ok(responseBody);
    }
}