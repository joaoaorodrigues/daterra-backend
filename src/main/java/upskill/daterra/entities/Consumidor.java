package upskill.daterra.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CONSUMIDOR")
public class Consumidor extends User {

    private String profileImageUrl;
    @ManyToMany
    @JoinTable(
            name = "favoritos",
            joinColumns = @JoinColumn(name = "consumidor_id"),
            inverseJoinColumns = @JoinColumn(name = "produtor_id")
    )
    private List<Produtor> produtoresFavoritos = new ArrayList<>();

    public List<Produtor> getProdutoresFavoritos() {
        return produtoresFavoritos;
    }

    public void setProdutoresFavoritos(List<Produtor> produtoresFavoritos) {
        this.produtoresFavoritos = produtoresFavoritos;
    }


    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

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

