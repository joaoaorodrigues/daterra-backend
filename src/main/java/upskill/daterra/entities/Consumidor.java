package upskill.daterra.entities;

import jakarta.persistence.Entity;

@Entity
public class Consumidor extends User {
    private String shippingAddress;

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}

