package upskill.daterra.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upskill.daterra.entities.Admin;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.entities.User;
import upskill.daterra.models.LoginModel;
import upskill.daterra.repositories.AdminRepository;
import upskill.daterra.repositories.ConsumidorRepository;
import upskill.daterra.repositories.ProdutorRepository;
import upskill.daterra.repositories.UserRepository;

import java.util.Optional;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticateUser(LoginModel loginModel) {
        User user = userRepository.findByEmail(loginModel.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(loginModel.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        if (user instanceof Admin) return "ADMIN";
        if (user instanceof Produtor) return "PRODUTOR";
        if (user instanceof Consumidor) return "CONSUMIDOR";

        throw new BadCredentialsException("Invalid user type");
    }
}

