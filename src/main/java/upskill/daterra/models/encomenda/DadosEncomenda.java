package upskill.daterra.models.encomenda;

import upskill.daterra.entities.Encomenda;
import upskill.daterra.models.consumidor.DadosConsumidor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DadosEncomenda {

    private Long id;
    private String deliveryOptions;
    private LocalDateTime dataEncomenda;
    private boolean isFulfilled;
    private DadosConsumidor consumidor;
    private List<DadosItemEncomenda> items;

    public DadosEncomenda(){}
    public DadosEncomenda(Encomenda encomenda) {
        this.id = encomenda.getId();
        this.deliveryOptions = encomenda.getDeliveryOptions();
        this.dataEncomenda = encomenda.getDataEncomenda();
        this.isFulfilled = encomenda.isFulfilled();
        this.consumidor = new DadosConsumidor(encomenda.getConsumidor());
        this.items = encomenda.getItems().stream()
                .map(DadosItemEncomenda::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryOptions() {
        return deliveryOptions;
    }

    public void setDeliveryOptions(String deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
    }

    public LocalDateTime getDataEncomenda() {
        return dataEncomenda;
    }

    public void setDataEncomenda(LocalDateTime dataEncomenda) {
        this.dataEncomenda = dataEncomenda;
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        isFulfilled = fulfilled;
    }

    public DadosConsumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(DadosConsumidor consumidor) {
        this.consumidor = consumidor;
    }

    public List<DadosItemEncomenda> getItems() {
        return items;
    }

    public void setItems(List<DadosItemEncomenda> items) {
        this.items = items;
    }
}
