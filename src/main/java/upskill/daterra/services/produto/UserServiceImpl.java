package upskill.daterra.services.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upskill.daterra.entities.User;
import upskill.daterra.models.CriarConsumidorModel;
import upskill.daterra.models.CriarProdutorModel;
import upskill.daterra.repository.UserRepository;

@Service

public class UserServiceImpl implements UserService {


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepository userRepository;


    @Override
    public User loginUser(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if(user != null && user.getId() != null) {
            if(passwordEncoder.matches(password, user.getPassword())) {
                return user;
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
