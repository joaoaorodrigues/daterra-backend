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

    @PostConstruct
    public void initializeCategories() {
        Map<Long, String> categoriesMap = Map.of(
                1L, "Biológico",
                2L, "Frutas",
                3L, "Hortícolas",
                4L, "Vinhos",
                5L, "Apícolas",
                6L, "Ervas e Especiarias",
                7L, "Processados"
        );

        for (Map.Entry<Long, String> entry : categoriesMap.entrySet()) {
            Long id = entry.getKey();
            String categoryName = entry.getValue();

            if (!categoryRepository.existsByName(categoryName)) {
                Category category = new Category();
                category.setId(id);
                category.setName(categoryName);
                categoryRepository.save(category);
            }
        }
    }
//    public void initializeCategories() {
//        List<String> categoriesList = Arrays.asList(
//                "Hortícolas",
//                "Frutas",
//                "Vinhos",
//                "Apícolas",
//                "Ervas e Especiarias",
//                "Processados",
//                "Biológico"
//        );
//
//        for (String categoryName : categoriesList) {
//            if (!categoryRepository.existsByName(categoryName)) {
//                Category category = new Category();
//                category.setName(categoryName);
//                categoryRepository.save(category);
//            }
//        }
//    }
}