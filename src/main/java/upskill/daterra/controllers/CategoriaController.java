package upskill.daterra.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Categoria;
import upskill.daterra.repositories.CategoriaRepository;

import java.util.List;


@RestController
@RequestMapping("/admin/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Categoria> getAllCategories() {
        return categoriaRepository.findAll();
    }

    @PostMapping("/criar")
    public ResponseEntity<?> addCategory(@RequestBody Categoria categoria) {
        if (categoriaRepository.existsByName(categoria.getName())) {
            return ResponseEntity.badRequest().body("Categoria já existe.");
        }
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryViaPost(@PathVariable Long id) {
        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Categoria não existe.");
        }

        categoriaRepository.deleteById(id);
        return ResponseEntity.ok("Categoria removida com sucesso.");
    }
}
