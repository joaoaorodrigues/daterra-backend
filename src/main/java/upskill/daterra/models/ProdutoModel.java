package upskill.daterra.models;

import upskill.daterra.entities.Category;
import upskill.daterra.entities.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoModel {
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
    private String productImageUrl;
    private List<Long> categories;


    public ProdutoModel() {

    }
    public ProdutoModel(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();
        this.productImageUrl = produto.getProductImageUrl();
        this.categories = produto.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toList());
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
