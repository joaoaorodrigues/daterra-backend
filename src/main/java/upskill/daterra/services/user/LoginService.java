package upskill.daterra.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.LoginModel;
import upskill.daterra.repositories.ConsumidorRepository;
import upskill.daterra.repositories.ProdutorRepository;

import java.util.Optional;

@Service
public class LoginService {

    private final ConsumidorRepository consumidorRepository;
    private final ProdutorRepository produtorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(ConsumidorRepository consumidorRepository, ProdutorRepository produtorRepository, PasswordEncoder passwordEncoder) {
        this.consumidorRepository = consumidorRepository;
        this.produtorRepository = produtorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticateUser(LoginModel loginModel) {
        String email = loginModel.getEmail();
        String password = loginModel.getPassword();

        Optional<Consumidor> consumidorOpt = consumidorRepository.findByEmail(email);
        if (consumidorOpt.isPresent()) {
            Consumidor consumidor = consumidorOpt.get();
            if (passwordEncoder.matches(password, consumidor.getPassword())) {
                return "CONSUMIDOR";
            }
        }


        Optional<Produtor> produtorOpt = produtorRepository.findByEmail(email);
        if (produtorOpt.isPresent()) {
            Produtor produtor = produtorOpt.get();
            if (passwordEncoder.matches(password, produtor.getPassword())) {
                return "PRODUTOR";
            }
        }

        return "INVALIDO";
    }
}
