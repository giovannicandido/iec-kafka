package com.example.ecommerce.estoque.service;

import com.example.ecommerce.estoque.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public void retirarEstoque(Long idProduto, int quantidade) {
        estoqueRepository.findById(idProduto)
                .ifPresent(estoque -> {
                    estoque.setQuantidade(estoque.getQuantidade() - quantidade);
                    estoqueRepository.save(estoque);
                });
    }
}
