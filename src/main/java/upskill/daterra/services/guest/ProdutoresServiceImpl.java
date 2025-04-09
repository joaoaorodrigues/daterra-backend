package upskill.daterra.services.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upskill.daterra.entities.Produtor;
import upskill.daterra.models.ProdutorMapInfo;
import upskill.daterra.models.auth_models.ProdutorModel;
import upskill.daterra.repositories.ProdutorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoresServiceImpl implements ProdutoresService{

    @Autowired
    private ProdutorRepository produtorRepository;

    @Override
    public List<ProdutorMapInfo> getProdutoresMapInfo() {
        List<Produtor> approvedProdutores = produtorRepository.findByIsApproved(true);
        return mapDadosProdutores(approvedProdutores);
    }

    @Override
    public List<Produtor> listarProdutores() {
        return produtorRepository.findAll();
    }
    @Override
    public Optional<Produtor> getProdutor(Long produtorId) {
        Optional<Produtor> produtor = produtorRepository.findById(produtorId);
        return produtor;
    }

    private List<ProdutorMapInfo> mapDadosProdutores(List<Produtor> produtores) {
        List<ProdutorMapInfo> produtorMapInfoList = new ArrayList<>();
        if (produtores == null) {
            return produtorMapInfoList;
        }
        for (Produtor produtor : produtores) {
            ProdutorMapInfo model = new ProdutorMapInfo();

            model.setIdProdutor(produtor.getId());
            model.setBusinessName(produtor.getBusinessName());
            model.setAddress(produtor.getAddress());
            model.setCategories(produtor.getCategories());
            model.setLatitude(produtor.getLatitude() != null ? produtor.getLatitude() : 0.0); // Default to 0.0
            model.setLongitude(produtor.getLongitude() != null ? produtor.getLongitude() : 0.0);

            produtorMapInfoList.add(model);
        }
        System.out.println(produtorMapInfoList);
        return produtorMapInfoList;
    }

}
