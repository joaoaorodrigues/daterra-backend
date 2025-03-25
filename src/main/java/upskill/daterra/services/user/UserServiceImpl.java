package upskill.daterra.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upskill.daterra.entities.User;
import upskill.daterra.models.CriarConsumidorModel;
import upskill.daterra.models.CriarProdutorModel;
import upskill.daterra.repositories.UserRepository;

import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepository userRepository;


    @Override
    public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            User foundUser = user.get();  // Unwrap the Optional and get the User object
            if (passwordEncoder.matches(password, foundUser.getPassword())) {
                return foundUser;
            }
        }
        return null;
    }

    @Override
    public User registerConsumidor(CriarConsumidorModel model) {
        return null;
    }

    @Override
    public User registerProdutor(CriarProdutorModel model) {
        return null;
    }
}
