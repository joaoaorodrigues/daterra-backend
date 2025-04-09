package upskill.daterra.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class AppReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texto")
    private String mensagem;

    @Column(name = "nome_remetente")
    private String nomeAutor;

    @Column(name = "data")
    private LocalDate dataCriacao;

    private Boolean visto = false;

    private String resolucao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }
}


