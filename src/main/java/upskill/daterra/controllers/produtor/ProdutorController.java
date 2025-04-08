package upskill.daterra.controllers.produtor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.auth_models.ProdutorModel;
import upskill.daterra.repositories.ProdutorRepository;

import java.util.Optional;

@RestController
@RequestMapping("/produtor")
public class ProdutorController {

    @Autowired
    private ProdutorRepository produtorRepository;

    @GetMapping("/perfil")
    public ResponseEntity<ProdutorModel> getCurrentProdutor() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        return produtorRepository.findByEmail(email)
                .filter(produtor -> produtor.getId() != null)
                .map(produtor -> {
                    ProdutorModel model = new ProdutorModel(produtor);
                    return ResponseEntity.ok(model);
                })
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtor> getProdutorByEmail(@ModelAttribute("id") Produtor produtor) {
        if(produtor == null || produtor.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtor> updateProdutor(@PathVariable Long id, @RequestBody Produtor produtor) {
        if (!produtorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtor.setId(id);
        Produtor updatedProdutor = produtorRepository.save(produtor);
        return ResponseEntity.ok(updatedProdutor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProdutor(@PathVariable Long id) {
        if (!produtorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtorRepository.deleteById(id);
        return ResponseEntity.ok("Produtor apagado com sucesso");
    }
}
