package upskill.daterra.services.encomenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upskill.daterra.entities.*;
import upskill.daterra.models.encomenda.DadosEncomenda;
import upskill.daterra.models.encomenda.EncomendaModel;
import upskill.daterra.models.encomenda.ItemEncomendaModel;
import upskill.daterra.repositories.ConsumidorRepository;
import upskill.daterra.repositories.EncomendaRepository;
import upskill.daterra.repositories.ProdutoRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import upskill.daterra.repositories.ProdutorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EncomendaServiceImpl implements EncomendaService{


    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutorRepository produtorRepository;
    @Autowired
    private EncomendaRepository encomendaRepository;
    @Autowired
    private ConsumidorRepository consumidorRepository;

    @Override
    public ResponseEntity<?> processarEncomendas(List<EncomendaModel> encomendasModel) {

        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        Optional<Consumidor> optionalConsumidor = consumidorRepository.findByEmail(email);

        if (optionalConsumidor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Consumidor consumidor = optionalConsumidor.get();

        List<Encomenda> encomendas = new ArrayList<>();

        for (EncomendaModel encomendaModel : encomendasModel) {

            for (ItemEncomendaModel itemModel : encomendaModel.getItems()) {
                Produto produto = produtoRepository.findById(itemModel.getProdutoId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

                if (produto.getQuantity() < itemModel.getQuantity()) {
                    return ResponseEntity.badRequest().body(
                            "Stock insuficiente para o produto: " + produto.getName()
                    );
                }
            }


            Encomenda encomenda = new Encomenda();
            Produtor produtor = produtorRepository.findById(encomendaModel.getProdutorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtor não encontrado"));
            encomenda.setProdutor(produtor);
            encomenda.setConsumidor(consumidor);
            encomenda.setAddress(encomendaModel.getAddress());
            encomenda.setNif(encomendaModel.getNif());
            encomenda.setDeliveryOptions(encomendaModel.getDeliveryOptions());


            List<ItemEncomenda> items = new ArrayList<>();
            for (ItemEncomendaModel itemModel : encomendaModel.getItems()) {
                Produto produto = produtoRepository.findById(itemModel.getProdutoId()).get();

                ItemEncomenda item = new ItemEncomenda();
                item.setProduto(produto);
                item.setQuantity(itemModel.getQuantity());
                item.setPrice(produto.getPrice());
                item.setEncomenda(encomenda);

                // Atualizar stock
                produto.setQuantity(produto.getQuantity() - itemModel.getQuantity());
                produtoRepository.save(produto);

                items.add(item);
            }

            encomenda.setItems(items);
            encomendas.add(encomenda);
        }

        encomendaRepository.saveAll(encomendas);


        List<Long> ids = encomendas.stream()
                .map(Encomenda::getId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ids);

    }
}
