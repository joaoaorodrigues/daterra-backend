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
import upskill.daterra.entities.Encomenda;
import upskill.daterra.entities.User;
import upskill.daterra.models.UpdateConsumidorModel;
import upskill.daterra.models.auth_models.ConsumidorModel;
import upskill.daterra.models.encomenda.DadosEncomenda;
import upskill.daterra.models.encomenda.EncomendaModel;
import upskill.daterra.repositories.ConsumidorRepository;
import upskill.daterra.repositories.EncomendaRepository;
import upskill.daterra.services.encomenda.EncomendaService;
import upskill.daterra.services.image.ImageService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consumidor")
public class ConsumidorController {

    @Autowired
    private ConsumidorRepository consumidorRepository;
    @Autowired
    private EncomendaRepository encomendaRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private EncomendaService encomendaService;

    @GetMapping("/perfil")
    public ResponseEntity<ConsumidorModel> getCurrentConsumidor() {
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();

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
    @PutMapping(value= "/update", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> updateProfile(
            @ModelAttribute UpdateConsumidorModel consumidorModel,
            @RequestPart(value="profileImage", required = false) MultipartFile profileImage
    ) {

        String email = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();

        Optional<Consumidor> optionalConsumidor = consumidorRepository.findByEmail(email);
        if (optionalConsumidor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {

            Consumidor consumidor = optionalConsumidor.get();

            consumidor.setFirstName(consumidorModel.getFirstName());
            consumidor.setLastName(consumidorModel.getLastName());
            consumidor.setPhone(consumidorModel.getPhone());
            consumidor.setAddress(consumidorModel.getAddress());
            consumidor.setCity(consumidorModel.getCity());
            consumidor.setRegion(consumidorModel.getCountry());
            consumidor.setPostalCode(consumidorModel.getPostalCode());
            consumidor.setNif(consumidorModel.getNif());


            if (profileImage != null && !profileImage.isEmpty()) {
                String profileImageUrl = imageService.storeImageFile(profileImage);
                consumidor.setProfileImageUrl(profileImageUrl);
            }

            consumidorRepository.save(consumidor);

            return ResponseEntity.ok(new ConsumidorModel(consumidor));
        } catch (Exception e) {
            System.out.println("outro erro"+e.getMessage());
            return ResponseEntity.internalServerError().body("Error updating profile");
        }
    }

    // criar encomendas:
    @PostMapping("/encomendas")
    public ResponseEntity<?> criarEncomendas(@RequestBody List<EncomendaModel> encomendasModel) {
        return encomendaService.processarEncomendas(encomendasModel);
    }

    // dados de encomenda efetuada:
    @GetMapping("/encomendas")
    public List<DadosEncomenda> getEncomendasByIds(@RequestParam List<Long> ids) {
        return encomendaRepository.findAllById(ids)
                .stream().map(DadosEncomenda::new).collect(Collectors.toList());
    }

    // historico de encomendas:
    @GetMapping("/historico-encomendas")
    public List<DadosEncomenda> listarEncomendas(){
        List<Encomenda> encomendas = encomendaRepository.findAll();
        List<DadosEncomenda> dadosEncomendas = encomendas.stream()
                .map(encomenda -> new DadosEncomenda(encomenda))
                .collect(Collectors.toList());
        return dadosEncomendas;
    }

    // dados de encomenda por id:
    @GetMapping("/encomenda/{id}")
    public ResponseEntity<DadosEncomenda> getEncomenda(@PathVariable Long id){
        Optional<Encomenda> optionalEncomenda = encomendaRepository.findById(id);
        if (optionalEncomenda.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Encomenda encomenda = optionalEncomenda.get();
        DadosEncomenda dadosEncomenda = new DadosEncomenda(encomenda);
        return ResponseEntity.ok(dadosEncomenda);
    }


}
