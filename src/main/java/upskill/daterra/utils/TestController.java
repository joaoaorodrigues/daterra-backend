package upskill.daterra.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestAdminCreator adminCreator;

    @GetMapping("/create-admin")
    public ResponseEntity<String> createAdmin() {
        adminCreator.createTestAdmin();
        return ResponseEntity.ok("Admin created successfully");
    }
}



