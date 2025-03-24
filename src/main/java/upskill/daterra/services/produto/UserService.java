package upskill.daterra.services.produto;

import upskill.daterra.entities.User;
import upskill.daterra.models.CriarConsumidorModel;
import upskill.daterra.models.CriarProdutorModel;

public interface UserService {
    User loginUser(String email, String password);

    User registerConsumidor(CriarConsumidorModel model);
    User registerProdutor(CriarProdutorModel model);
}
