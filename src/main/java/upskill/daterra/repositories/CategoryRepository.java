package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}
