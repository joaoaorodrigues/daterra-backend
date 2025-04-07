package upskill.daterra.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONSUMIDOR")
public class Consumidor extends User {
    @Override
    public String toString() {
        return "Consumidor{" +
                ", birthDate=" + birthDate +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}

