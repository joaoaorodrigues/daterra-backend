package upskill.daterra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upskill.daterra.models.CriarConsumidorModel;
import upskill.daterra.models.CriarProdutorModel;
import upskill.daterra.services.auth.RegistrationService;

@RestController
@RequestMapping("/public")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/contas/registo")
    public ResponseEntity<String> registerBuyer(@RequestBody CriarConsumidorModel model) {
        registrationService.registerBuyer(model);
        return ResponseEntity.ok("Buyer registered successfully");
    }

    @PostMapping("/contas/registoprodutor")
    public ResponseEntity<String> registerSeller(@RequestBody CriarProdutorModel model) {
        registrationService.registerSeller(model);
        return ResponseEntity.ok("Seller registered successfully");
    }
}
