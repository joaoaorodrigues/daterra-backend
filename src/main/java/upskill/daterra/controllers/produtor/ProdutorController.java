package upskill.daterra.controllers.produtor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Category;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.UpdateProdutorModel;
import upskill.daterra.models.auth_models.ProdutorModel;
import upskill.daterra.repositories.CategoryRepository;
import upskill.daterra.repositories.ProdutorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtor")
public class ProdutorController {

    @Autowired
    private ProdutorRepository produtorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/perfil")
    public ResponseEntity<ProdutorModel> getCurrentProdutor() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        return produtorRepository.findByEmail(email)
                .filter(produtor -> produtor.getId() != null)
                .map(produtor -> {
                    ProdutorModel model = new ProdutorModel(produtor);
                    System.out.println(model);
                    return ResponseEntity.ok(model);
                })
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProdutorModel produtorModel) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Produtor> optionalProdutor = produtorRepository.findByEmail(email);
        if (optionalProdutor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produtor produtor = optionalProdutor.get();

        produtor.setFirstName(produtorModel.getFirstName());
        produtor.setLastName(produtorModel.getLastName());
        produtor.setPhone(produtorModel.getPhone());
        produtor.setAddress(produtorModel.getAddress());
        produtor.setCity(produtorModel.getCity());
        produtor.setRegion(produtorModel.getRegion());
        produtor.setPostalCode(produtorModel.getPostalCode());
        produtor.setBusinessName(produtorModel.getBusinessName());
        produtor.setDescription(produtorModel.getDescription());
        produtor.setHasPickupOption(produtorModel.isHasPickupOption());
        produtor.setHasDeliveryOption(produtorModel.isHasDeliveryOption());
        produtor.setOrganicCertificate(produtorModel.getOrganicCertificate());

        List<Category> categories = categoryRepository.findAllById(produtorModel.getCategories());
        produtor.setCategories(categories);

        produtorRepository.save(produtor);

        return ResponseEntity.ok(new ProdutorModel(produtor));
    }



}
