package com.example.ecommerce.ecommerce.controller.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class VendaRequest {
    @NotNull
    private Long idCliente;
    @Size(min = 1)
    private List<Long> idProdutos;
    @NotBlank
    private String numeroCartao;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<Long> getIdProdutos() {
        return idProdutos;
    }

    public void setIdProdutos(List<Long> idProdutos) {
        this.idProdutos = idProdutos;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
