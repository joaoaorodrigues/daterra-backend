package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
    Optional<Category> findByName(String name);
}
