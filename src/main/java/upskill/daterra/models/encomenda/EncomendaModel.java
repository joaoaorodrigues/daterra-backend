package upskill.daterra.models.encomenda;

import java.util.List;

public class EncomendaModel {

    private Long produtorId;
    private String deliveryOptions;
    private List<ItemEncomendaModel> items;
    private String address;
    private String nif;


    public Long getProdutorId() {
        return produtorId;
    }

    public void setProdutorId(Long produtorId) {
        this.produtorId = produtorId;
    }

    public String getDeliveryOptions() {
        return deliveryOptions;
    }

    public void setDeliveryOptions(String deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
    }

    public List<ItemEncomendaModel> getItems() {
        return items;
    }

    public void setItems(List<ItemEncomendaModel> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
}
