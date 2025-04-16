package upskill.daterra.services.produto;

import org.springframework.web.multipart.MultipartFile;
import upskill.daterra.entities.Category;
import upskill.daterra.entities.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    //Produto criarProduto( Produto produto);

    List<Produto> listarProdutosPorProdutor(Long produtorId);

    Produto atualizarProduto(Long id, Long produtorId, Produto produto, MultipartFile arquivo);

    void apagarProduto(Long productId);

    List<Category> getCategories();

    Optional<Produto> getProduto(Long productId, Long produtorId);
}
