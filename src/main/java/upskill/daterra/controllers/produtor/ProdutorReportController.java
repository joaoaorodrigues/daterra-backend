package upskill.daterra.controllers.produtor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.AppReport;
import upskill.daterra.entities.User;
import upskill.daterra.repositories.AppReportRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;

@RestController
@RequestMapping("/produtor/reports")
public class ProdutorReportController {

    @Autowired
    private AppReportRepository reportRepo;

    @PostMapping("/criar")
    public ResponseEntity<AppReport> criarReport(@RequestBody AppReport report) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        report.setAutor(user);
        report.setNomeAutor(user.getFirstName() + " " + user.getLastName());
        report.setDataCriacao(LocalDate.now());
        report.setVisto(false);
        report.setResolucao(null);

        AppReport saved = reportRepo.save(report);
        return ResponseEntity.ok(saved);
    }
}
