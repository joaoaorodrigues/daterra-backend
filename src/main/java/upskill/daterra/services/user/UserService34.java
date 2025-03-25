//package upskill.daterra.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import upskill.daterra.entities.Consumidor;
//import upskill.daterra.entities.Produtor;
//import upskill.daterra.models.CriarConsumidorModel;
//import upskill.daterra.models.CriarProdutorModel;
//import upskill.daterra.repositories.ConsumidorRepository;
//import upskill.daterra.repositories.ProdutorRepository;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private ConsumidorRepository consumidorRepository;
//
//    @Autowired
//    private ProdutorRepository produtorRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public void registerConsumidor(CriarConsumidorModel model) {
//        Consumidor consumidor = new Consumidor();
//        consumidor.setEmail(model.getEmail());
//        consumidor.setPassword(passwordEncoder.encode(model.getPassword()));
//        consumidor.setFirstName(model.getFirstName());
//        consumidor.setLastName(model.getLastName());
//        consumidor.setPhone(model.getPhone());
//        consumidor.setAddress(model.getAddress());
//        consumidor.setCity(model.getCity());
//        consumidor.setCountry(model.getCountry());
//        consumidor.setPostalCode(model.getPostalCode());
//        consumidor.setNif(model.getNif());
//        consumidor.setBirthDate(model.getBirthDate());
//
//        consumidorRepository.save(consumidor);
//    }
//
//    public void registerProdutor(CriarProdutorModel model) {
//        Produtor produtor = new Produtor();
//        produtor.setEmail(model.getEmail());
//        produtor.setPassword(passwordEncoder.encode(model.getPassword()));
//        produtor.setFirstName(model.getFirstName());
//        produtor.setLastName(model.getLastName());
//        produtor.setPhone(model.getPhone());
//        produtor.setAddress(model.getAddress());
//        produtor.setCity(model.getCity());
//        produtor.setCountry(model.getCountry());
//        produtor.setPostalCode(model.getPostalCode());
//        produtor.setNif(model.getNif());
//        produtor.setBusinessName(model.getBusinessName());
//        produtor.setIban(model.getIban());
//        produtor.setBirthDate(model.getBirthDate());
//
//        produtorRepository.save(produtor);
//    }
//    @Autowired
//    public UserService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//}
