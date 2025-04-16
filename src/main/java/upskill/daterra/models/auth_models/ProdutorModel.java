package upskill.daterra.models.auth_models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import upskill.daterra.entities.Category;
import upskill.daterra.entities.Produtor;

import java.time.LocalDate;
import java.util.List;

public class ProdutorModel {

    private String tipoUtilizador;
    private String email;
    private String businessName;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String region;
    private String country;
    private String postalCode;
    private List<Category> categories;
    private String nif;
    private boolean isApproved;
    private boolean hasDeliveryOption;
    private boolean hasPickupOption;
    private String description;
    private String organicCertificate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String profileImageUrl;
    private String coverImageUrl;


    public ProdutorModel() {
    }

    public ProdutorModel(Produtor produtor) {
        this.tipoUtilizador = produtor.getClass().getSimpleName().toLowerCase();
        this.email = produtor.getEmail();
        this.businessName = produtor.getBusinessName();
        this.firstName = produtor.getFirstName();
        this.lastName = produtor.getLastName();
        this.birthDate = produtor.getBirthDate();
        this.phone = produtor.getPhone();
        this.address = produtor.getAddress();
        this.city = produtor.getCity();
        this.region=produtor.getRegion();
        this.country = produtor.getCountry();
        this.postalCode = produtor.getPostalCode();
        this.categories = produtor.getCategories();
        this.nif = produtor.getNif();
        this.isApproved = produtor.isApproved();
        this.hasDeliveryOption = produtor.hasDeliveryOption();
        this.hasPickupOption = produtor.hasPickupOption();
        this.description = produtor.getDescription();
        this.organicCertificate = produtor.getOrganicCertificate();
        this.profileImageUrl = produtor.getProfileImageUrl();
        this.coverImageUrl = produtor.getCoverImageUrl();
    }

    public String getTipoUtilizador() {
        return tipoUtilizador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isHasDeliveryOption() {
        return hasDeliveryOption;
    }

    public void setHasDeliveryOption(boolean hasDeliveryOption) {
        this.hasDeliveryOption = hasDeliveryOption;
    }

    public boolean isHasPickupOption() {
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOrganicCertificate() {
        return organicCertificate;
    }

    public void setOrganicCertificate(String organicCertificate) {
        this.organicCertificate = organicCertificate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
}
