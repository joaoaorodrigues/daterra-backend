package upskill.daterra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.models.LoginModel;
import upskill.daterra.services.user.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginModel loginModel) {
        String userType = loginService.authenticateUser(loginModel);

        if ("CONSUMIDOR".equals(userType)) {
            return ResponseEntity.ok("Consumidor Homepage");
        } else if ("PRODUTOR".equals(userType)) {
            return ResponseEntity.ok("Produtor Homepage");
        } else {
            return ResponseEntity.status(401).body("O utilizador não existe.");
        }
    }
}