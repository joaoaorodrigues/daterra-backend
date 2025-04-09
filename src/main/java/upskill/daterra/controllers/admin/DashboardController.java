package upskill.daterra.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upskill.daterra.repositories.ConsumidorRepository;
import upskill.daterra.repositories.ProdutoRepository;
import upskill.daterra.repositories.ProdutorRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/dashboard")
public class DashboardController {

    @Autowired
    private ProdutorRepository produtorRepository;

    @Autowired
    private ConsumidorRepository consumidorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/stats")
    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("produtorCount", produtorRepository.count());
        stats.put("consumidorCount", consumidorRepository.count());
        stats.put("produtoCount", produtoRepository.count());
        stats.put("vendasCount", 0L); // Replace later when you add Encomenda

        return stats;
    }
}
