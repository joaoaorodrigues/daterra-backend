package upskill.daterra.services.encomenda;

import org.springframework.http.ResponseEntity;
import upskill.daterra.models.encomenda.EncomendaModel;

import java.util.List;

public interface EncomendaService {

    public ResponseEntity<?> processarEncomendas(List<EncomendaModel> encomendas);

}
