package upskill.daterra.services.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upskill.daterra.entities.Produto;
import upskill.daterra.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto criarProduto(Long userId, Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutosPorProdutor(Long produtorId) {
        return produtoRepository.findByProdutorId(produtorId);
    }

    public Optional<Produto> procurarProdutoPorIdEProdutor(Long id, Long produtorId) {
        return produtoRepository.findByIdAndProdutorId(id, produtorId);
    }

    public Produto atualizarProduto(Long id, Long produtorId, Produto produtoAtualizado) {
        return produtoRepository.findByIdAndProdutorId(id, produtorId)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDescricao(produtoAtualizado.getDescricao());
                    produto.setPreco(produtoAtualizado.getPreco());
                    produto.setQuantidade(produtoAtualizado.getQuantidade());
                    return produtoRepository.save(produto);
                }).orElseThrow(() -> new RuntimeException("Produto não encontrado ou não tem permissão para editar."));
    }

    public void apagarProduto(Long id, Long produtorId) {
        Produto produto = produtoRepository.findByIdAndProdutorId(id, produtorId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado ou não tem permissão para remover."));
        produtoRepository.delete(produto);
    }
}
