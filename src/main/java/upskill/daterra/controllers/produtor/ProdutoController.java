package upskill.daterra.controllers.produtor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upskill.daterra.entities.Category;
import upskill.daterra.entities.Produto;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.ProdutoModel;
import upskill.daterra.repositories.CategoryRepository;
import upskill.daterra.repositories.ProdutoRepository;
import upskill.daterra.repositories.ProdutorRepository;
import upskill.daterra.services.image.ImageService;
import upskill.daterra.services.produto.ProdutoService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtor/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutorRepository produtorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ImageService imageService;

    @PostMapping("/criar")
    public ResponseEntity<Produto> criarProduto(
            @RequestParam("nome") String nome,
            @RequestParam("descricao") String descricao,
            @RequestParam("preco") Double preco,
            @RequestParam("quantidade") Integer quantidade,
            @RequestParam("categorias[]") Integer[] categorias,
            @RequestPart(value = "productImage", required = false) MultipartFile productImage) throws IOException {

        List<Integer> lista_categorias = Arrays.asList(categorias);

        System.out.println("lista_categorias" + lista_categorias);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Produtor> optionalProdutor = produtorRepository.findByEmail(email);

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        produto.setProdutor(optionalProdutor.get());

        if (productImage != null && !productImage.isEmpty()) {
            String productImagePath = imageService.storeImageFile(productImage);
            produto.setProductImageUrl(productImagePath);
        }
        Produto createdProduto = produtoService.criarProduto(produto);
        return ResponseEntity.ok(createdProduto);
    }

    @GetMapping("/{produtorId}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Long produtorId) {
        return ResponseEntity.ok(produtoService.listarProdutosPorProdutor(produtorId));
    }

    @PutMapping(value = "/{produtoId}/atualizar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> atualizarProduto(
            @RequestPart("userData") String userDataJson,
            @PathVariable Long produtoId,
            @RequestPart(value = "productImage", required = false) MultipartFile productImage
    ) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Produtor> optionalProdutor = produtorRepository.findByEmail(email);
        if (optionalProdutor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Produto> optionalProduto = produtoRepository.findByIdAndProdutorId(produtoId, optionalProdutor.get().getId());
        if (optionalProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProdutoModel produtoModel = objectMapper.readValue(userDataJson, ProdutoModel.class);

            Produto produto = optionalProduto.get();

            produto.setNome(produtoModel.getNome());
            produto.setDescricao(produtoModel.getDescricao());
            produto.setPreco(produtoModel.getPreco());
            produto.setQuantidade(produtoModel.getQuantidade());
            List<Category> categories = categoryRepository.findAllById(produtoModel.getCategories());
            produto.setCategories(categories);

            if (productImage != null && !productImage.isEmpty()) {
                String imageUrl = imageService.storeImageFile(productImage);
                produto.setProductImageUrl(imageUrl);
            }

            produtoRepository.save(produto);

            return ResponseEntity.ok(produto);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Invalid JSON format");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error updating product");
        }
    }

    @DeleteMapping("/deletar/{produtorId}/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long produtorId, @PathVariable Long id) {
        produtoService.apagarProduto(id, produtorId);
        return ResponseEntity.ok("Produto removido com sucesso");
    }
}
