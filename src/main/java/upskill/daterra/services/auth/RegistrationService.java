package upskill.daterra.services.auth;

import upskill.daterra.models.CriarConsumidorModel;
import upskill.daterra.models.CriarProdutorModel;

public interface RegistrationService {
    void registerConsumidor(CriarConsumidorModel model);
    void registerProdutor(CriarProdutorModel model);
}
