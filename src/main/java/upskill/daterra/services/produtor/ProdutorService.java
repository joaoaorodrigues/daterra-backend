package upskill.daterra.services.produtor;

import upskill.daterra.models.ProdutorMapInfo;
import upskill.daterra.repositories.ProdutorProjection;


import java.util.List;

public interface ProdutorService {
//    List<ProdutorMapInfoDTO> getProdutoresMapInfo();

    List<ProdutorMapInfo> getProdutoresMapInfo();

}
