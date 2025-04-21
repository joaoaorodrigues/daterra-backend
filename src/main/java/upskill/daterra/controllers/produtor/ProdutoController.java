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
import upskill.daterra.entities.User;
import upskill.daterra.models.ProdutoModel;
import upskill.daterra.models.auth_models.ProdutorModel;
import upskill.daterra.repositories.CategoryRepository;
import upskill.daterra.repositories.ProdutoRepository;
import upskill.daterra.repositories.ProdutorRepository;
import upskill.daterra.services.guest.ProdutoresService;
import upskill.daterra.services.image.ImageService;
import upskill.daterra.services.produto.ProdutoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/produtor/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutorRepository produtorRepository;

    @Autowired
    private ProdutoresService produtoresService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);


    @PostMapping(value="/criar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public  ResponseEntity<?> criarProduto(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("pricingUnit") String pricingUnit,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("categories") List<Category> categories,
            @RequestPart(value = "productImageUrl", required = false) MultipartFile productImage,
            HttpServletRequest request) throws IOException {

        System.out.println("Received request with Content-Type: " + request.getContentType());
        System.out.println("Categories : " + categories);
        System.out.println("Product image : " + (productImage != null ? productImage.getOriginalFilename() : "sem imagem"));

        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        Optional<Produtor> optionalProdutor = produtorRepository.findByEmail(email);

        try {

            Produto produto = new Produto();
            produto.setName(name);
            produto.setDescription(description);
            produto.setPrice(price);
            produto.setPricingUnit(pricingUnit);
            produto.setQuantity(quantity);
            produto.setProdutor(optionalProdutor.get());
            produto.setCategories(categories);

            if (productImage != null && !productImage.isEmpty()) {
                String productImagePath = imageService.storeImageFile(productImage);
                produto.setProductImageUrl(productImagePath);
            }

            produtoRepository.save(produto);

            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            System.out.println("Erro a adicionar produto: "+e.getMessage());
            return ResponseEntity.internalServerError().body("Error adding produto");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listarProdutos() {
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        Optional<Produtor> optionalProdutor = produtorRepository.findByEmail(email);

        if (optionalProdutor == null) {
            return ResponseEntity.notFound().build();
        }

        Produtor produtor = optionalProdutor.get();
        List<Produto> produtos = produtoRepository.findByProdutorId(produtor.getId());

        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produtos);
    }

    @PutMapping(value = "/update/{produtoId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduto(
            @RequestPart("userData") String userDataJson,
            @PathVariable Long produtoId,
            @RequestPart(value = "productImage", required = false) MultipartFile productImage
    ) {
        //String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();

        Optional<Produtor> optionalProdutor = produtorRepository.findByEmail(email);
        if (optionalProdutor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
        if (optionalProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProdutoModel produtoModel = objectMapper.readValue(userDataJson, ProdutoModel.class);

            Produto produto = optionalProduto.get();

            produto.setName(produtoModel.getName());
            produto.setDescription(produtoModel.getDescription());
            produto.setPrice(produtoModel.getPrice());
            produto.setPricingUnit(produtoModel.getPricingUnit());
            produto.setQuantity(produtoModel.getQuantity());
            produto.setCategories(produtoModel.getCategories());

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

    @DeleteMapping("/apagar/{productId}")
    public ResponseEntity<String> apagarProduto(@PathVariable Long productId) {
        produtoService.apagarProduto(productId);
        return ResponseEntity.ok("Produto removido com sucesso");
    }
}
