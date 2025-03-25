package upskill.daterra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.models.CriarConsumidorModel;
import upskill.daterra.models.CriarProdutorModel;
import upskill.daterra.services.auth.RegistrationService;

@RestController
@RequestMapping("/criar-conta")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public ResponseEntity<String> getRegistrationInfo() {
        return ResponseEntity.ok("Tudo certo!");
    }

    @PostMapping("/consumidor")
    public ResponseEntity<String> registerConsumidor(@RequestBody CriarConsumidorModel model) {
        registrationService.registerConsumidor(model);
        return ResponseEntity.ok("Consumidor registado com sucesso.");
    }

    @PostMapping("/produtor")
    public ResponseEntity<String> registerProdutor(@RequestBody CriarProdutorModel model) {
        registrationService.registerProdutor(model);
        return ResponseEntity.ok("Produtor registado com sucesso.");
    }
}
