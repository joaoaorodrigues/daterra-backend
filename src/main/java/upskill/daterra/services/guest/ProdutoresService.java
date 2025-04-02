package upskill.daterra.services.guest;

import upskill.daterra.entities.Produtor;
import upskill.daterra.models.ProdutorMapInfo;

import java.util.List;
import java.util.Optional;

public interface ProdutoresService {

    List<ProdutorMapInfo> getProdutoresMapInfo();
    List<Produtor> listarProdutores();

    Optional<Produtor> getProdutor(Long produtorId);


}
