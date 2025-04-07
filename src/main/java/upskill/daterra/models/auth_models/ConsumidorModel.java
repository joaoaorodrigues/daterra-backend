package upskill.daterra.models.auth_models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import upskill.daterra.entities.Consumidor;

import java.time.LocalDate;

public class ConsumidorModel {

    private String tipoUtilizador;
    private String email;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    public ConsumidorModel (Consumidor consumidor){
        this.tipoUtilizador = consumidor.getClass().getSimpleName().toUpperCase();
        this.email = consumidor.getEmail();
        this.firstName = consumidor.getFirstName();
        this.lastName = consumidor.getLastName();
        this.birthDate = consumidor.getBirthDate();
    }

    public String getTipoUtilizador(){
        return tipoUtilizador;
    }
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }

}
