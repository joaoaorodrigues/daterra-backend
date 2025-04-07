package upskill.daterra.controllers.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upskill.daterra.entities.Admin;
import upskill.daterra.entities.Consumidor;
import upskill.daterra.entities.Produtor;
import upskill.daterra.entities.User;
import upskill.daterra.models.auth_models.ConsumidorModel;
import upskill.daterra.models.auth_models.ProdutorModel;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            if (user instanceof Produtor){
                Produtor produtor = (Produtor) user;
                return ResponseEntity.ok(new ProdutorModel(produtor));
            } else {
                if (user instanceof Consumidor){
                    Consumidor consumidor = (Consumidor) user;
                    return ResponseEntity.ok(new ConsumidorModel(consumidor));
                }
            }
            return ResponseEntity.ok(new UserModel(user));
        } else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected principal type: " + principal.getClass().getName());
        }
    }

    public static class UserModel {
        private final String nome;
        private final String email;
        private final String tipoUtilizador;

        public UserModel(User user) {
            this.nome = user.getFirstName();
            this.email = user.getEmail();
            this.tipoUtilizador = user instanceof Admin ? "ADMIN" :
                    user instanceof Produtor ? "PRODUTOR" : "CONSUMIDOR";
        }

        public String getNome() {
            return nome;
        }
        public String getEmail() {
            return email;
        }
        public String getTipoUtilizador() {
            return tipoUtilizador;
        }
    }
}



