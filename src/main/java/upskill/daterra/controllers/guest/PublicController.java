package upskill.daterra.controllers.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import upskill.daterra.entities.Category;
import upskill.daterra.entities.Produto;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.ProdutorMapInfo;
import upskill.daterra.services.guest.ProdutoresService;
import upskill.daterra.services.produto.ProdutoService;

import java.util.List;
import java.util.Optional;

@RestController
public class PublicController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoresService produtoresService;

    @GetMapping("/produtores")
    public ResponseEntity<List<Produtor>> listarProdutores() {
        return ResponseEntity.ok(produtoresService.listarProdutores());
    }

    @GetMapping("/produtores/map-info")
    public List<ProdutorMapInfo> getProdutoresMapInfo() {
        return produtoresService.getProdutoresMapInfo();
    }

    @GetMapping("/produtores/{produtorId}")
    public ResponseEntity<Produtor> getProdutor(@PathVariable Long produtorId) {
        Optional<Produtor> produtor = produtoresService.getProdutor(produtorId);
        if (produtor.isPresent()) {
            return ResponseEntity.ok(produtor.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtor não encontrado");
        }
    }

    @GetMapping("/produtores/{produtorId}/produtos")
    public ResponseEntity<List<Produto>> listarProdutosPorProdutor(@PathVariable Long produtorId) {
        return ResponseEntity.ok(produtoService.listarProdutosPorProdutor(produtorId));
    }

    @GetMapping("/produtores/{produtorId}/produtos/{productId}")
    public ResponseEntity<Produto> getProduto(@PathVariable Long produtorId, @PathVariable Long productId) {
        Optional<Produto> produto = produtoService.getProduto(productId, produtorId);
        if(produto.isPresent()){
            return ResponseEntity.ok(produto.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

    @GetMapping("/produtos/categorias")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok(produtoService.getCategories());
    }
}