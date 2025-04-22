package upskill.daterra.models.consumidor;

import upskill.daterra.entities.Consumidor;

public class DadosConsumidor {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String nif;


    public DadosConsumidor() {}

    public DadosConsumidor(Consumidor consumidor) {
        this.id = consumidor.getId();
        this.name = consumidor.getFirstName() + " " + consumidor.getLastName();
        this.email = consumidor.getEmail();
        this.phone = consumidor.getPhone();
        this.address = consumidor.getAddress();
        this.city = consumidor.getCity();
        this.region = consumidor.getRegion();
        this.postalCode = consumidor.getPostalCode();
        this.country = consumidor.getCountry();
        this.nif = consumidor.getNif();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
}
