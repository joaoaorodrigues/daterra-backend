package upskill.daterra.services.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import upskill.daterra.entities.Category;
import upskill.daterra.entities.Image;
import upskill.daterra.entities.Produto;
import upskill.daterra.repositories.CategoryRepository;
import upskill.daterra.repositories.ImagesRepository;
import upskill.daterra.repositories.ProdutoRepository;
import upskill.daterra.services.image.ImageService;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImagesRepository imagesRepository;

//    public Produto criarProduto(Produto produto) {
//        return produtoRepository.save(produto);
//    }

    public List<Produto> listarProdutosPorProdutor(Long produtorId) {
        return produtoRepository.findByProdutorId(produtorId);
    }

    public Optional<Produto> procurarProdutoPorId(Long id, Long produtorId) {
        return produtoRepository.findById(id);
    }

    public Produto atualizarProduto(Long id, Long produtorId, Produto produtoAtualizado, MultipartFile productImage) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setName(produtoAtualizado.getName());
                    produto.setDescription(produtoAtualizado.getDescription());
                    produto.setPrice(produtoAtualizado.getPrice());
                    produto.setQuantity(produtoAtualizado.getQuantity());
                    Image imagem = new Image(
                            productImage.getOriginalFilename(),
                            productImage.getContentType()
                    );
                    imagesRepository.save(imagem);

                    String productImagePath = imageService.storeImageFile(productImage);
                    produto.setProductImageUrl(productImagePath);

                    return produtoRepository.save(produto);
                }).orElseThrow(() -> new RuntimeException("Produto não encontrado ou não tem permissão para editar."));
    }

    public void apagarProduto(Long productId) {
        Produto produto = produtoRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado ou não tem permissão para remover."));
        produtoRepository.delete(produto);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Produto> getProduto(Long productId, Long produtorId){
        return produtoRepository.findById(productId);
    }

}
