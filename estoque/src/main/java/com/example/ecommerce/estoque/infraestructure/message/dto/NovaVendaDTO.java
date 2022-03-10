package com.example.ecommerce.estoque.infraestructure.message.dto;

import java.util.List;

public class NovaVendaDTO {
    private Long id;
    private String cpfCliente;
    private Double total;
    private List<Long> codProdutos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Long> getCodProdutos() {
        return codProdutos;
    }

    public void setCodProdutos(List<Long> codProdutos) {
        this.codProdutos = codProdutos;
    }
}
