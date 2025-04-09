package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.Consumidor;

import java.util.Optional;

public interface ConsumidorRepository extends JpaRepository<Consumidor, Long> {
    Optional<Consumidor> findByEmail(String email);

    long count();

}
