package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.Encomenda;
import upskill.daterra.entities.Image;
import upskill.daterra.entities.Produtor;

import java.util.List;

public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {


    List<Encomenda> findByFulfilled(Boolean fulfilled);
    List<Encomenda> findByProdutor(Produtor produtor);
    List<Encomenda> findByProdutorAndFulfilled(Produtor produtor, Boolean fulfilled);
}
