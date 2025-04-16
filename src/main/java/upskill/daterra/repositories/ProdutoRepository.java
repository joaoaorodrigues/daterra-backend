package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByProdutorId(Long produtorId);
    Optional<Produto> findById(Long productId);
}
