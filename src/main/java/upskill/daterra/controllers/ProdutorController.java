package upskill.daterra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.ProdutorMapInfo;
import upskill.daterra.repositories.ProdutorProjection;
import upskill.daterra.repositories.ProdutorRepository;
import upskill.daterra.services.produtor.ProdutorService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtores")
public class ProdutorController {

    @Autowired
    private ProdutorService produtorService;

    @Autowired
    private ProdutorRepository produtorRepository;

    @GetMapping("/map-info")
    public List<ProdutorMapInfo> getProdutoresMapInfo() {
        return produtorService.getProdutoresMapInfo();
    }

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
