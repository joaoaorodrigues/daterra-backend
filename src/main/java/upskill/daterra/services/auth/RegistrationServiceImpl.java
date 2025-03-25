package upskill.daterra.services.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.CriarConsumidorModel;
import upskill.daterra.models.CriarProdutorModel;
import upskill.daterra.repositories.UserRepository;
import upskill.daterra.services.produto.UserService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder,
                                   UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void registerBuyer(CriarConsumidorModel model) {
        validatePasswords(model.getPassword(), model.getConfirmPassword());

        Consumidor consumidor = new Consumidor();
        mapBuyerFields(model, consumidor);
        consumidor.setPassword(passwordEncoder.encode(model.getPassword()));
        userRepository.save(consumidor);
    }

    @Override
    @Transactional
    public void registerSeller(CriarProdutorModel model) {
        validatePasswords(model.getPassword(), model.getConfirmPassword());

        Produtor produtor = new Produtor();
        mapSellerFields(model, produtor);
        produtor.setPassword(passwordEncoder.encode(model.getPassword()));
        userRepository.save(produtor);
    }

    private void validatePasswords(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    private void mapBuyerFields(CriarConsumidorModel model, Consumidor consumidor) {
        consumidor.setEmail(model.getEmail());
        consumidor.setFirstName(model.getFirstName());
        consumidor.setLastName(model.getLastName());
        consumidor.setPhone(model.getPhone());
        consumidor.setAddress(model.getAddress());
        consumidor.setCity(model.getCity());
        consumidor.setCountry(model.getCountry());
        consumidor.setPostalCode(model.getPostalCode());
        consumidor.setNif(model.getNif());
    }

    private void mapSellerFields(CriarProdutorModel model, Produtor produtor) {
        produtor.setEmail(model.getEmail());
        produtor.setFirstName(model.getFirstName());
        produtor.setLastName(model.getLastName());
        produtor.setBusinessName(model.getBusinessName());
        produtor.setPhone(model.getPhone());
        produtor.setAddress(model.getAddress());
        produtor.setCity(model.getCity());
        produtor.setCountry(model.getCountry());
        produtor.setPostalCode(model.getPostalCode());
        produtor.setNif(model.getNif());
        produtor.setIban(model.getIban());
    }
}
