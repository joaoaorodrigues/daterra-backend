package upskill.daterra.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Produtor;
import upskill.daterra.repositories.ProdutorRepository;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ProdutorRepository produtorRepository;

    @Autowired
    public AdminController(ProdutorRepository produtorRepository) {
        this.produtorRepository = produtorRepository;
    }

    @GetMapping("/produtores")
    public List<Produtor> getAllProdutores() {
        return produtorRepository.findAll();
    }

    @GetMapping("/produtores/pendentes")
    public List<Produtor> getPendingProdutores() {
        return produtorRepository.findByIsApproved(false);
    }
    

    @PostMapping("/produtores/validar/{id}")
    public ResponseEntity<String> approveProdutor(@PathVariable Long id) {
        return produtorRepository.findById(id).map(produtor -> {
            produtor.setApproved(true);
            produtorRepository.save(produtor);
            return ResponseEntity.ok("Produtor validado com sucesso.");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/produtores/rejeitar/{id}")
    public ResponseEntity<String> rejectProdutor(@PathVariable Long id) {
        if (!produtorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtorRepository.deleteById(id);
        return ResponseEntity.ok("Produtor rejeitado e removido.");
    }

}


