package upskill.daterra.models;

import upskill.daterra.entities.Category;
import upskill.daterra.entities.Produto;

import java.util.List;

public class ProdutoModel {
    private String name;
    private String description;
    private Double price;
    private String pricingUnit;
    private Integer quantity;
    private String productImageUrl;
    private List<Category> categories;


    public ProdutoModel() {

    }
    public ProdutoModel(Produto produto) {
        this.name = produto.getName();
        this.description = produto.getDescription();
        this.price = produto.getPrice();
        this.pricingUnit = produto.getPricingUnit();
        this.quantity = produto.getQuantity();
        this.productImageUrl = produto.getProductImageUrl();
        this.categories = produto.getCategories();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getPricingUnit() {
        return pricingUnit;
    }

    public void setPricingUnit(String pricingUnit) {
        this.pricingUnit = pricingUnit;
    }
}
