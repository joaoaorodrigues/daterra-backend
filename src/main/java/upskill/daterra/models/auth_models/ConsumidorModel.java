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
    private String nif;
    private String phone;
    private String Address;
    private String City;
    private String Country;
    private String PostalCode;
    private String region;

    public ConsumidorModel (Consumidor consumidor){
        this.tipoUtilizador = consumidor.getClass().getSimpleName().toLowerCase();
        this.email = consumidor.getEmail();
        this.firstName = consumidor.getFirstName();
        this.lastName = consumidor.getLastName();
        this.birthDate = consumidor.getBirthDate();
        this.nif = consumidor.getNif();
        this.phone = consumidor.getPhone();
        this.Address = consumidor.getAddress();
        this.City = consumidor.getCity();
        this.Country = consumidor.getCountry();
        this.PostalCode = consumidor.getPostalCode();
        this.region = consumidor.getRegion();
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
