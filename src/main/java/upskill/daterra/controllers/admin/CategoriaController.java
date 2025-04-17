package upskill.daterra.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Category;
import upskill.daterra.repositories.CategoryRepository;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/categorias")
public class CategoriaController {

    private final CategoryRepository categoryRepository;

    public CategoriaController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/criar")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            return ResponseEntity.badRequest().body("Categoria já existe.");
        }
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryViaPost(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Categoria não existe.");
        }

        categoryRepository.deleteById(id);
        return ResponseEntity.ok("Categoria removida com sucesso.");
    }

}