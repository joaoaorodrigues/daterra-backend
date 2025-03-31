package upskill.daterra.services.produtor;

import upskill.daterra.entities.Produtor;
import upskill.daterra.models.ProdutorMapInfo;
import upskill.daterra.repositories.ProdutorProjection;
import java.util.Optional;


import java.util.List;

public interface ProdutorService {
//    List<ProdutorMapInfoDTO> getProdutoresMapInfo();

    List<ProdutorMapInfo> getProdutoresMapInfo();

    List<Produtor> listarProdutores();

    Optional<Produtor> getProdutor(Long produtorId);
}
