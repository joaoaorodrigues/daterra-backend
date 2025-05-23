package upskill.daterra.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("PRODUTOR")

public class Produtor extends User {
    private String businessName;

    @ManyToMany
    @JoinTable(
            name = "produtor_category",
            joinColumns = @JoinColumn(name = "produtor_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @OneToMany
    private List<Produto> produtos;


    private Double latitude;
    private Double longitude;

    private boolean isApproved;

    private boolean hasDeliveryOption;
    private boolean hasPickupOption;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String organicCertificate;

    private String profileImageUrl;
    private String coverImageUrl;
    private LocalDate creationDate = LocalDate.now();

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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @JsonProperty("hasDeliveryOption")
    public boolean hasDeliveryOption() {
        return hasDeliveryOption;
    }

    public void setHasDeliveryOption(boolean hasDeliveryOption) {

        this.hasDeliveryOption = hasDeliveryOption;
    }
    @JsonProperty("hasPickupOption")
    public boolean hasPickupOption() {
        return hasPickupOption;
    }

    public void setHasPickupOption(boolean hasPickupOption) {
        this.hasPickupOption = hasPickupOption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganicCertificate() {
        return organicCertificate;
    }

    public void setOrganicCertificate(String organicCertificate) {
        this.organicCertificate = organicCertificate;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Produtor{" +
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
                ", businessName='" + businessName + '\'' +
                ", categories='" + categories + '\'' +
                ", approved?='" + isApproved + '\'' +
                ", delivery option?='" + hasDeliveryOption + '\'' +
                ", pickup option?='" + hasPickupOption + '\'' +
                ", organicCertificate='" + organicCertificate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
