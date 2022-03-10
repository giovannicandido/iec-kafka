package com.example.ecommerce.notafiscal.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NotaFiscal {
    @Id
    @GeneratedValue
    private Long id;
    private String cpfCliente;
    private Double valor;
    private String codigoRetornoReceita;
    private String xml;

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCodigoRetornoReceita() {
        return codigoRetornoReceita;
    }

    public void setCodigoRetornoReceita(String codigoRetornoReceita) {
        this.codigoRetornoReceita = codigoRetornoReceita;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
}
