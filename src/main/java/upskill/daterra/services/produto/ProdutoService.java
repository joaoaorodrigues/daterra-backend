package upskill.daterra.services.produto;

import upskill.daterra.entities.Category;
import upskill.daterra.entities.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    Produto criarProduto(Long userId, Produto produto);

    List<Produto> listarProdutosPorProdutor(Long produtorId);

    Produto atualizarProduto(Long id, Long produtorId, Produto produto);

    void apagarProduto(Long id, Long produtorId);

    List<Category> getCategories();

    Optional<Produto> getProduto(Long productId, Long produtorId);
}
