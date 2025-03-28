package upskill.daterra.services.produtor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.CriarConsumidorModel;
import upskill.daterra.models.ProdutorMapInfo;
import upskill.daterra.repositories.ProdutorProjection;
import upskill.daterra.repositories.ProdutorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutorServiceImpl implements ProdutorService {

    @Autowired
    private ProdutorRepository produtorRepository;

    @Override
    public List<ProdutorMapInfo> getProdutoresMapInfo() {
        List<Produtor> approvedProdutores = produtorRepository.findByIsApproved(true);
        return mapDadosProdutores(approvedProdutores);
    }
    private List<ProdutorMapInfo> mapDadosProdutores(List<Produtor> produtores) {
        List<ProdutorMapInfo> produtorMapInfoList = new ArrayList<>();
        for (Produtor produtor : produtores) {
            ProdutorMapInfo model = new ProdutorMapInfo();

            model.setIdProdutor(produtor.getId());
            model.setBusinessName(produtor.getBusinessName());
            model.setAddress(produtor.getAddress());
            model.setCategories(produtor.getCategories());
            model.setLatitude(produtor.getLatitude());
            model.setLongitude(produtor.getLongitude());

            produtorMapInfoList.add(model);
        }
        return produtorMapInfoList;
    }

}


