package upskill.daterra.models;

import upskill.daterra.entities.Category;
import upskill.daterra.entities.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoModel {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String productImageUrl;
    private List<Long> categories;


    public ProdutoModel() {

    }
    public ProdutoModel(Produto produto) {
        this.name = produto.getName();
        this.description = produto.getDescription();
        this.price = produto.getPrice();
        this.quantity = produto.getQuantity();
        this.productImageUrl = produto.getProductImageUrl();
        this.categories = produto.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toList());
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


    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

}
