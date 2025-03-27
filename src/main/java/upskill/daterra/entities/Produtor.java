package upskill.daterra.entities;

import jakarta.persistence.Entity;

@Entity
public class Produtor extends User {
    private String businessName;
    private String iban;
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }


    @Override
    public String toString() {
        return "Produtor{" +
                "isAdmin=" + isAdmin +
                ", birthDate=" + birthDate +
                ", nif='" + nif + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", iban='" + iban + '\'' +
                ", businessName='" + businessName + '\'' +
                '}';
    }
}
