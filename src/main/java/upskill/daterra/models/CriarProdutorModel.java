package upskill.daterra.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import upskill.daterra.entities.Category;

import java.time.LocalDate;
import java.util.List;

public class CriarProdutorModel {
    private String email;
    private String businessName;
    private String description;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String postalCode;

    private String region;

    private boolean hasDeliveryOption;
    private boolean hasPickupOption;

    private String organicCertificate;

    private List<Long> categories;
    private String nif;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<Long> getCategories() {
        return categories;
    }
    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public boolean hasDeliveryOption() {
        return hasDeliveryOption;
    }

    public void setHasDeliveryOption(boolean hasDeliveryOption) {
        this.hasDeliveryOption = hasDeliveryOption;
    }

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
}
