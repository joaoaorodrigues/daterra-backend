package upskill.daterra.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.AppReport;
import upskill.daterra.repositories.AppReportRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/reports")
public class ReportController {

    @Autowired
    private AppReportRepository reportRepo;

    @GetMapping("/pendentes")
    public List<AppReport> getPendentes() {
        return reportRepo.findByVisto(false);
    }

    @GetMapping("/vistos")
    public List<AppReport> getVistos() {
        return reportRepo.findByVisto(true);
    }

    @PostMapping("/resolver/{id}")
    public ResponseEntity<?> resolveReport(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return reportRepo.findById(id).map(report -> {
            String resolucao = body.get("resolucao");
            report.setVisto(true);
            report.setResolucao(resolucao); // "Fixed" or "Ignored"
            reportRepo.save(report);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
