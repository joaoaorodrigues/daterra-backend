package upskill.daterra.controllers.produtor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upskill.daterra.entities.Category;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.entities.User;
import upskill.daterra.models.UpdateProdutorModel;
import upskill.daterra.models.auth_models.ProdutorModel;
import upskill.daterra.repositories.CategoryRepository;
import upskill.daterra.repositories.ProdutorRepository;
import upskill.daterra.services.image.ImageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtor")
public class ProdutorController {

    @Autowired
    private ProdutorRepository produtorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageService imageService;

    @GetMapping("/perfil")
    public ResponseEntity<ProdutorModel> getCurrentProdutor() {
        //String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();

        return produtorRepository.findByEmail(email)
                .filter(produtor -> produtor.getId() != null)
                .map(produtor -> {
                    ProdutorModel model = new ProdutorModel(produtor);
                    System.out.println("modelo produtor:" + model);
                    return ResponseEntity.ok(model);
                })
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }

//    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> updateProfile(
//            @RequestPart("userData") String userDataJson,
//            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
//            @RequestPart(value = "coverImage", required = false) MultipartFile coverImage
//    ) {
//
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        Optional<Produtor> optionalProdutor = produtorRepository.findByEmail(email);
//        if (optionalProdutor.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        try {
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.registerModule(new JavaTimeModule());
//            ProdutorModel produtorModel = objectMapper.readValue(userDataJson, ProdutorModel.class);
//
//            Produtor produtor = optionalProdutor.get();
//
//            produtor.setFirstName(produtorModel.getFirstName());
//            produtor.setLastName(produtorModel.getLastName());
//            produtor.setPhone(produtorModel.getPhone());
//            produtor.setAddress(produtorModel.getAddress());
//            produtor.setCity(produtorModel.getCity());
//            produtor.setRegion(produtorModel.getRegion());
//            produtor.setPostalCode(produtorModel.getPostalCode());
//            produtor.setBusinessName(produtorModel.getBusinessName());
//            produtor.setDescription(produtorModel.getDescription());
//            produtor.setHasPickupOption(produtorModel.isHasPickupOption());
//            produtor.setHasDeliveryOption(produtorModel.isHasDeliveryOption());
//            produtor.setOrganicCertificate(produtorModel.getOrganicCertificate());
//            produtor.setCategories(produtorModel.getCategories());
//
//            if (profileImage != null && !profileImage.isEmpty()) {
//                String profileImageUrl = imageService.storeImageFile(profileImage);
//                produtor.setProfileImageUrl(profileImageUrl);
//            }
//
//            if (coverImage != null && !coverImage.isEmpty()) {
//                String coverImagePath = imageService.storeImageFile(coverImage);
//                produtor.setCoverImageUrl(coverImagePath);
//            }
//
//            produtorRepository.save(produtor);
//
//            return ResponseEntity.ok(new ProdutorModel(produtor));
//        } catch (JsonProcessingException e) {
//            System.out.println("erro ao converter json" + e.getMessage());
//            return ResponseEntity.badRequest().body("Invalid JSON format");
//        } catch (Exception e) {
//            System.out.println("outro erro" + e.getMessage());
//            return ResponseEntity.internalServerError().body("Error updating profile");
//        }
//    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProfile(
            @ModelAttribute UpdateProdutorModel produtorModel,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestPart(value = "coverImage", required = false) MultipartFile coverImage
    ) {
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        Optional<Produtor> optionalProdutor = produtorRepository.findByEmail(email);

        if (optionalProdutor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
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
            produtor.setHasDeliveryOption(produtorModel.isHasDeliveryOption());
            produtor.setHasPickupOption(produtorModel.isHasPickupOption());
            produtor.setOrganicCertificate(produtorModel.getOrganicCertificate());

            //List<Category> categories = categoryRepository.findAllById(produtorModel.getCategories());
            produtor.setCategories(produtorModel.getCategories());

            if (profileImage != null && !profileImage.isEmpty()) {
                String profileImageUrl = imageService.storeImageFile(profileImage);
                produtor.setProfileImageUrl(profileImageUrl);
            }

            if (coverImage != null && !coverImage.isEmpty()) {
                String coverImageUrl = imageService.storeImageFile(coverImage);
                produtor.setCoverImageUrl(coverImageUrl);
            }

            produtorRepository.save(produtor);
            return ResponseEntity.ok(new ProdutorModel(produtor));

        } catch (Exception e) {
            System.err.println("Error updating profile: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error updating profile");
        }
    }



}