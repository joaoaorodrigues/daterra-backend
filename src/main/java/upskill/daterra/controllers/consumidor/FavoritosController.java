package upskill.daterra.controllers.consumidor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.entities.User;
import upskill.daterra.repositories.ConsumidorRepository;
import upskill.daterra.repositories.ProdutorRepository;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritosController {

    private static final Logger log = LoggerFactory.getLogger(FavoritosController.class);
    @Autowired
    private ConsumidorRepository consumidorRepo;

    @Autowired
    private ProdutorRepository produtorRepo;

    private Consumidor getAuthenticatedConsumidor() {
        Object principal =SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof User))
            throw new RuntimeException("Sem login");
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        return consumidorRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Consumidor não encontrado"));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@RequestParam Long produtorId) {
        Consumidor consumidor = getAuthenticatedConsumidor();
        Produtor produtor = produtorRepo.findById(produtorId)
                .orElseThrow(() -> new RuntimeException("Produtor não encontrado"));

        if (!consumidor.getProdutoresFavoritos().contains(produtor)) {
            consumidor.getProdutoresFavoritos().add(produtor);
            consumidorRepo.save(consumidor);
        }

        return ResponseEntity.ok("Produtor added to favorites");
    }

    @DeleteMapping("/remover")
    public ResponseEntity<String> remover(@RequestParam Long produtorId) {
        Consumidor consumidor = getAuthenticatedConsumidor();
        consumidor.getProdutoresFavoritos().removeIf(p -> p.getId().equals(produtorId));
        consumidorRepo.save(consumidor);

        return ResponseEntity.ok("Produtor removed from favorites");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produtor>> listar() {
        Consumidor consumidor = getAuthenticatedConsumidor();
        List<Produtor> favoritos = consumidor.getProdutoresFavoritos();

        if (favoritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(favoritos);
    }
}

