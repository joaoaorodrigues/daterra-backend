package upskill.daterra.models;

import upskill.daterra.entities.Category;

import java.util.List;

public class ProdutorMapInfo {

    private Long idProdutor;
    private String businessName;
    private String address;
    private List<Category> categories;
    private double latitude;
    private double longitude;

    public Long getIdProdutor() {
        return idProdutor;
    }

    public void setIdProdutor(Long idProdutor) {
        this.idProdutor = idProdutor;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
