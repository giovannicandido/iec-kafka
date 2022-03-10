package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.controller.request.VendaRequest;
import com.example.ecommerce.ecommerce.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class VendaController {
    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<?> criarVenda(@RequestBody VendaRequest request) {
        vendaService.confirmarVenda(request.getIdCliente(), request.getIdProdutos(), request.getNumeroCartao());
        return ResponseEntity.ok().build();
    }
}
