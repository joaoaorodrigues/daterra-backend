package upskill.daterra.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}