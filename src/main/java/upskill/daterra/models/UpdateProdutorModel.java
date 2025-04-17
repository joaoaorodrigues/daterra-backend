package upskill.daterra.models;

import upskill.daterra.entities.Category;

import java.util.List;

public class UpdateProdutorModel {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String businessName;
    private String description;
    private boolean hasDeliveryOption;
    private boolean hasPickupOption;
    private String organicCertificate;
    private List<Category> categories;

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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getOrganicCertificate() {
        return organicCertificate;
    }

    public void setOrganicCertificate(String organicCertificate) {
        this.organicCertificate = organicCertificate;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
