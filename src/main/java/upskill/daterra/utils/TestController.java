package upskill.daterra.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    private TestAdminCreator adminCreator;

    @GetMapping("/criar-admin")
    public ResponseEntity<String> createAdmin() {
        adminCreator.createTestAdmin();
        return ResponseEntity.ok("Admin criado com sucesso!");
    }
}



