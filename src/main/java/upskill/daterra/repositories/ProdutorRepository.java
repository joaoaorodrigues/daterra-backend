package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.Produtor;

import java.util.List;
import java.util.Optional;



public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
    Optional<Produtor> findByEmail(String email);
    List<Produtor> findByIsApproved(boolean isApproved);

}
