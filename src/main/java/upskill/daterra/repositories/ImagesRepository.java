package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.Image;

public interface ImagesRepository extends JpaRepository<Image, Long> {
}
