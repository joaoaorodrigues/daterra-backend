package upskill.daterra.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.CriarConsumidorModel;
import upskill.daterra.models.CriarProdutorModel;
import upskill.daterra.repository.UserRepository;
import upskill.daterra.services.produto.UserService;

@RestController
@RequestMapping("/public")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    @Autowired
    private UserService userService;

    @PostMapping("/contas/registo")
    public ResponseEntity<String> registerBuyer(@RequestBody CriarConsumidorModel model) {
        Consumidor consumidor = new Consumidor();
        consumidor.setEmail(model.getEmail());
        consumidor.setPassword(passwordEncoder.encode(model.getPassword()));
        consumidor.setConfirmPassword(passwordEncoder.encode(model.getConfirmPassword()));
        consumidor.setFirstName(model.getFirstName());
        consumidor.setLastName(model.getLastName());
        consumidor.setPhone(model.getPhone());
        consumidor.setAddress(model.getAddress());
        consumidor.setCity(model.getCity());
        consumidor.setCountry(model.getCountry());
        consumidor.setPostalCode(model.getPostalCode());
        consumidor.setNif(model.getNif());




        userRepository.save(consumidor);
        userService.registerConsumidor(model);

        return ResponseEntity.ok("Buyer registered successfully");
    }

    @PostMapping("/register/seller")
    public ResponseEntity<String> registerSeller(@RequestBody CriarProdutorModel model) {
        Produtor produtor = new Produtor();
        produtor.setEmail(model.getEmail());
        produtor.setPassword(passwordEncoder.encode(model.getPassword()));
        produtor.setConfirmPassword(passwordEncoder.encode(model.getConfirmPassword()));
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


        userRepository.save(produtor);
        userService.registerProdutor(model);

        return ResponseEntity.ok("Seller registered successfully");
    }
}