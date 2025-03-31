package upskill.daterra.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Category;
import upskill.daterra.repositories.CategoriaRepository;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoriaRepository.findAll();
    }

    @PostMapping("/criar")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        if (categoriaRepository.existsByName(category.getName())) {
            return ResponseEntity.badRequest().body("Categoria já existe.");
        }
        return ResponseEntity.ok(categoriaRepository.save(category));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryViaPost(@PathVariable Long id) {
        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Categoria não existe.");
        }

        categoriaRepository.deleteById(id);
        return ResponseEntity.ok("Categoria removida com sucesso.");
    }

    @PostConstruct
    public void initializeCategories() {
        List<String> categoriesList = Arrays.asList(
                "Hortícolas",
                "Frutas",
                "Vinhos",
                "Apícolas",
                "Ervas e Especiarias",
                "Processados",
                "Biológico"
        );

        for (String categoryName : categoriesList) {
            if (!categoriaRepository.existsByName(categoryName)) {
                Category category = new Category();
                category.setName(categoryName);
                categoriaRepository.save(category);
            }
        }
    }
}