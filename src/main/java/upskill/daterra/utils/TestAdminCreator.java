package upskill.daterra.utils;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upskill.daterra.entities.Admin;
import upskill.daterra.repositories.UserRepository;

@Service
public class TestAdminCreator {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void createTestAdmin() {

        if (userRepository.findByEmail("admin@test.com").isEmpty()) {
            String rawPassword = "admin123";
            String hashedPassword = passwordEncoder.encode(rawPassword);
            System.out.println("HASHED PASSWORD: " + hashedPassword);

            Admin admin = new Admin();
            admin.setEmail("admin@test.com");
            admin.setPassword(hashedPassword);
            admin.setFirstName("System");
            admin.setLastName("Administrator");
            userRepository.save(admin);
            System.out.println("TEST ADMIN CREATED: admin@test.com / admin123");
        }
    }
}
