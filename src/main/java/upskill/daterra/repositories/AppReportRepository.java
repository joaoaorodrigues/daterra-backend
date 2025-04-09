package upskill.daterra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upskill.daterra.entities.AppReport;

import java.util.List;

public interface AppReportRepository extends JpaRepository<AppReport, Long> {
    List<AppReport> findByVisto(boolean visto);
}
