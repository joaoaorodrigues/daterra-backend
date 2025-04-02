package upskill.daterra.services.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upskill.daterra.entities.Category;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.CriarConsumidorModel;
import upskill.daterra.models.CriarProdutorModel;
import upskill.daterra.repositories.CategoryRepository;
import upskill.daterra.repositories.UserRepository;
import upskill.daterra.services.guest.GeocodingService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final GeocodingService geocodingService;


    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository,
                                   CategoryRepository categoryRepository,
                                   PasswordEncoder passwordEncoder,
                                   GeocodingService geocodingService) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;
        this.geocodingService = geocodingService;
    }

    @Override
    @Transactional
    public void registerConsumidor(CriarConsumidorModel model) {
        validatePasswords(model.getPassword(), model.getConfirmPassword());

        Consumidor consumidor = new Consumidor();
        mapDadosConsumidor(model, consumidor);
        consumidor.setPassword(passwordEncoder.encode(model.getPassword()));
        userRepository.save(consumidor);
    }

    @Override
    @Transactional
    public void registerProdutor(CriarProdutorModel model) {
        validatePasswords(model.getPassword(), model.getConfirmPassword());
        Produtor produtor = new Produtor();
        mapDadosProdutor(model, produtor);
        try {
            GeocodingService.Coordinates coordinates = geocodingService.getCoordinates(
                    produtor.getAddress(),
                    produtor.getCity(),
                    produtor.getCountry(),
                    produtor.getPostalCode()
            );

            if (coordinates != null) {
                produtor.setLatitude(coordinates.latitude);
                produtor.setLongitude(coordinates.longitude);
            }
        }catch (Exception e) {
            System.err.println("Error getting coordinates: " + e.getMessage());

        }

        System.out.println("Produtor object: " + produtor);
        produtor.setPassword(passwordEncoder.encode(model.getPassword()));
        userRepository.save(produtor);
    }

    private void validatePasswords(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    private void mapDadosConsumidor(CriarConsumidorModel model, Consumidor consumidor) {

        consumidor.setEmail(model.getEmail());
        consumidor.setFirstName(model.getFirstName());
        consumidor.setLastName(model.getLastName());
        consumidor.setBirthDate(model.getBirthDate());
        consumidor.setPhone(model.getPhone());
        consumidor.setAddress(model.getAddress());
        consumidor.setCity(model.getCity());
        consumidor.setCountry(model.getCountry());
        consumidor.setPostalCode(model.getPostalCode());
        consumidor.setNif(model.getNif());
    }

    private void mapDadosProdutor(CriarProdutorModel model, Produtor produtor) {
        produtor.setEmail(model.getEmail());
        produtor.setFirstName(model.getFirstName());
        produtor.setLastName(model.getLastName());
//        produtor.setBirthDate(model.getBirthDate());
        produtor.setBusinessName(model.getBusinessName());
        produtor.setPhone(model.getPhone());
        produtor.setAddress(model.getAddress());
        produtor.setCity(model.getCity());
        produtor.setCountry(model.getCountry());
        produtor.setPostalCode(model.getPostalCode());
        produtor.setNif(model.getNif());
//        produtor.setIban(model.getIban());
        List<Category> categories = new ArrayList<>();
        if (model.getCategories() != null) {
            for (Long categoryId : model.getCategories()) {
                Category category = categoryRepository.findById(categoryId).orElse(null);
                if (category != null) {
                    categories.add(category);
                }
            }
        }
        produtor.setCategories(categories);
    }
}
