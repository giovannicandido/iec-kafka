package com.example.ecommerce.ecommerce.model;

import javax.persistence.*;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Double valor;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoPagamento situacao;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public SituacaoPagamento getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoPagamento situacao) {
        this.situacao = situacao;
    }

    public TipoPagamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoPagamento tipo) {
        this.tipo = tipo;
    }
}
