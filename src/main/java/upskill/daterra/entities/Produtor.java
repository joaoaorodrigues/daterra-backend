package upskill.daterra.entities;

import jakarta.persistence.Entity;

@Entity
public class Produtor extends User {
    private String businessName;
    private String iban;


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
}
