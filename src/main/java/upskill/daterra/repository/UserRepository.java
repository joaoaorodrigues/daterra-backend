package upskill.daterra.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
}