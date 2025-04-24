package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.Encomenda;
import upskill.daterra.entities.Image;

import java.util.List;

public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {


    List<Encomenda> findByFulfilled(Boolean fulfilled);
}
