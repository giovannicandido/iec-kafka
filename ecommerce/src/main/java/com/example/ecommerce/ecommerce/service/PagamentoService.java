package com.example.ecommerce.ecommerce.service;

import org.springframework.stereotype.Service;

@Service
public class PagamentoService {
    public boolean confirmarPagamentoCartao(String numeroCartao, Double valor) {
        return true;
    }
}
