package upskill.daterra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.repositories.ConsumidorRepository;

import java.util.Optional;

@RestController
@RequestMapping("/consumidor")
public class ConsumidorController {

    @Autowired
    private ConsumidorRepository consumidorRepository;

    @GetMapping("/{email}")
    public ResponseEntity<Consumidor> getConsumidorByEmail(@PathVariable String email) {
        Optional<Consumidor> consumidor = consumidorRepository.findByEmail(email);
        return consumidor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConsumidor(@PathVariable Long id) {
        if (!consumidorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consumidorRepository.deleteById(id);
        return ResponseEntity.ok("Consumidor deletado com sucesso");
    }
}
