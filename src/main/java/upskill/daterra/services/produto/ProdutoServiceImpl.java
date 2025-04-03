package upskill.daterra.services.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import upskill.daterra.entities.Category;
import upskill.daterra.entities.Images;
import upskill.daterra.entities.Produto;
import upskill.daterra.repositories.CategoryRepository;
import upskill.daterra.repositories.ImagesRepository;
import upskill.daterra.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImagesRepository imagesRepository;

    public Produto criarProduto(Long userId, Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutosPorProdutor(Long produtorId) {
        return produtoRepository.findByProdutorId(produtorId);
    }

    public Optional<Produto> procurarProdutoPorIdEProdutor(Long id, Long produtorId) {
        return produtoRepository.findByIdAndProdutorId(id, produtorId);
    }

    public Produto atualizarProduto(Long id, Long produtorId, Produto produtoAtualizado, MultipartFile arquivo) {
        return produtoRepository.findByIdAndProdutorId(id, produtorId)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDescricao(produtoAtualizado.getDescricao());
                    produto.setPreco(produtoAtualizado.getPreco());
                    produto.setQuantidade(produtoAtualizado.getQuantidade());
                    produto.setImposto(produtoAtualizado.getImposto());
                    Images imagem = new Images(
                            arquivo.getOriginalFilename(),
                            arquivo.getContentType()
                    );
                    imagesRepository.save(imagem);

                    produto.setImagem(imagem);

                    return produtoRepository.save(produto);
                }).orElseThrow(() -> new RuntimeException("Produto não encontrado ou não tem permissão para editar."));
    }

    public void apagarProduto(Long id, Long produtorId) {
        Produto produto = produtoRepository.findByIdAndProdutorId(id, produtorId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado ou não tem permissão para remover."));
        produtoRepository.delete(produto);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Produto> getProduto(Long productId, Long produtorId){
        return produtoRepository.findByIdAndProdutorId(productId, produtorId);
    }

}
