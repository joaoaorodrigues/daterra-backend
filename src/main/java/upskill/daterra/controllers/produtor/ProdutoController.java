package upskill.daterra.controllers.produtor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upskill.daterra.entities.Produto;
import upskill.daterra.services.produto.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtor/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criar/{produtorId}")
    public ResponseEntity<Produto> criarProduto(@PathVariable Long userId, @RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.criarProduto(userId, produto));
    }

    @GetMapping("/{produtorId}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Long produtorId) {
        return ResponseEntity.ok(produtoService.listarProdutosPorProdutor(produtorId));
    }

    @PutMapping("/atualizar/{produtorId}/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long produtorId, @PathVariable Long id, @RequestBody Produto produto,  @RequestPart(value = "imagem", required = false) MultipartFile arquivo) {
        return ResponseEntity.ok(produtoService.atualizarProduto(id, produtorId, produto, arquivo));
    }

    @DeleteMapping("/deletar/{produtorId}/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long produtorId, @PathVariable Long id) {
        produtoService.apagarProduto(id, produtorId);
        return ResponseEntity.ok("Produto removido com sucesso");
    }
}
