package upskill.daterra.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String pricingUnit;
    private Integer quantity;
    private String ProductImageUrl;

    @ManyToMany
    @JoinTable(
            name = "produto_category",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;


    @ManyToOne
    @JoinColumn(name = "produtor_id", nullable = false)
    private Produtor produtor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Produtor getProdutor() { return produtor; }
    public void setProdutor(Produtor produtor) { this.produtor = produtor; }

    public String getProductImageUrl() {
        return ProductImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        ProductImageUrl = productImageUrl;
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
