package upskill.daterra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Produtor;
import upskill.daterra.repositories.ProdutorRepository;

import java.util.Optional;

@RestController
@RequestMapping("/produtor")
public class ProdutorController {

    @Autowired
    private ProdutorRepository produtorRepository;

    @GetMapping("/{email}")
    public ResponseEntity<Produtor> getProdutorByEmail(@PathVariable String email) {
        Optional<Produtor> produtor = produtorRepository.findByEmail(email);
        return produtor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProdutor(@PathVariable Long id) {
        if (!produtorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtorRepository.deleteById(id);
        return ResponseEntity.ok("Produtor deleted successfully");
    }
}
