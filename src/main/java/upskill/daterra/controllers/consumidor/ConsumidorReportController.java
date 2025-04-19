package upskill.daterra.controllers.consumidor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upskill.daterra.entities.AppReport;
import upskill.daterra.repositories.AppReportRepository;

import java.time.LocalDate;

@RestController
@RequestMapping("/cliente/reports")
public class ConsumidorReportController {

    @Autowired
    private AppReportRepository reportRepo;

    @PostMapping("/criar")
    public ResponseEntity<AppReport> criarReport(@RequestBody AppReport report) {
        report.setDataCriacao(LocalDate.now());
        report.setVisto(false);
        report.setResolucao(null);
        AppReport saved = reportRepo.save(report);
        return ResponseEntity.ok(saved);
    }

}
