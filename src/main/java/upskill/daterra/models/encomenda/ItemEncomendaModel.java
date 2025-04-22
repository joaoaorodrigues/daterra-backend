package upskill.daterra.models.encomenda;

public class ItemEncomendaModel {

    private Long produtoId;

    private String produtoName;

    private Integer quantity;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProdutoName() {
        return produtoName;
    }

    public void setProdutoName(String produtoName) {
        this.produtoName = produtoName;
    }
}
