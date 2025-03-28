package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByName(String name);
}
