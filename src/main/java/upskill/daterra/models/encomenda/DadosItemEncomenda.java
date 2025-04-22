package upskill.daterra.models.encomenda;

import upskill.daterra.entities.ItemEncomenda;

public class DadosItemEncomenda {

    private Long produtoId;
    private String produtoNome;
    private Double price;
    private Integer quantity;

    public DadosItemEncomenda() {}

    public DadosItemEncomenda(ItemEncomenda item) {
        this.produtoId = item.getProduto().getId();
        this.produtoNome = item.getProduto().getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
    }


    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
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
}
