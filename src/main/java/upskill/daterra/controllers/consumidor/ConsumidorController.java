package upskill.daterra.controllers.consumidor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.models.auth_models.ConsumidorModel;
import upskill.daterra.repositories.ConsumidorRepository;
import upskill.daterra.services.image.ImageService;

import java.util.Optional;

@RestController
@RequestMapping("/consumidor")
public class ConsumidorController {

    @Autowired
    private ConsumidorRepository consumidorRepository;

    @Autowired
    private ImageService imageService;

    @GetMapping("/perfil")
    public ResponseEntity<ConsumidorModel> getCurrentConsumidor() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return consumidorRepository.findByEmail(email)
                .filter(consumidor -> consumidor.getId() != null)
                .map(consumidor -> {
                    ConsumidorModel model = new ConsumidorModel(consumidor);
                    System.out.println("modelo consumidor:"+model);
                    return ResponseEntity.ok(model);
                })
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConsumidor(@PathVariable Long id) {
        if (!consumidorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consumidorRepository.deleteById(id);
        return ResponseEntity.ok("Consumidor deletado com sucesso");
    }
    @PutMapping(value= "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProfile(
            @RequestPart ("userData") String userDataJson,
            @RequestPart(value="profileImage", required = false) MultipartFile profileImage
    ) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Consumidor> optionalConsumidor = consumidorRepository.findByEmail(email);
        if (optionalConsumidor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ConsumidorModel consumidorModel = objectMapper.readValue(userDataJson, ConsumidorModel.class);

            Consumidor consumidor = optionalConsumidor.get();

            consumidor.setFirstName(consumidorModel.getFirstName());
            consumidor.setLastName(consumidorModel.getLastName());
            consumidor.setPhone(consumidorModel.getPhone());
            consumidor.setAddress(consumidorModel.getAddress());
            consumidor.setCity(consumidorModel.getCity());
            consumidor.setRegion(consumidorModel.getRegion());
            consumidor.setPostalCode(consumidorModel.getPostalCode());
            consumidor.setNif(consumidorModel.getNif());


            if (profileImage != null && !profileImage.isEmpty()) {
                String profileImageUrl = imageService.storeImageFile(profileImage);
                consumidor.setProfileImageUrl(profileImageUrl);
            }

            consumidorRepository.save(consumidor);

            return ResponseEntity.ok(new ConsumidorModel(consumidor));
        } catch (JsonProcessingException e) {
            System.out.println("erro ao converter json"+e.getMessage());
            return ResponseEntity.badRequest().body("Invalid JSON format");
        } catch (Exception e) {
            System.out.println("outro erro"+e.getMessage());
            return ResponseEntity.internalServerError().body("Error updating profile");
        }
    }
}
