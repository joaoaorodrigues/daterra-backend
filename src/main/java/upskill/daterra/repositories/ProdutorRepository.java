package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upskill.daterra.entities.Produtor;

import java.util.List;
import java.util.Optional;



public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
    Optional<Produtor> findByEmail(String email);
    List<Produtor> findByIsApproved(boolean isApproved);

    long count();


    @Query("SELECT p FROM Produtor p JOIN FETCH p.categories WHERE p.isApproved = true")
    List<Produtor> findApprovedWithCategories();

}