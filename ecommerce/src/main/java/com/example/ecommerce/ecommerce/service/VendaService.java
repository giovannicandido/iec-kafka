package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.exception.DomainBusinessException;
import com.example.ecommerce.ecommerce.infraestructure.message.kafka.VendaProducer;
import com.example.ecommerce.ecommerce.infraestructure.message.dto.NovaVendaDTO;
import com.example.ecommerce.ecommerce.model.*;
import com.example.ecommerce.ecommerce.repository.ClienteRepository;
import com.example.ecommerce.ecommerce.repository.ProdutoRepository;
import com.example.ecommerce.ecommerce.repository.VendaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {
    private final PagamentoService pagamentoService;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final VendaRepository vendaRepository;
    private final VendaProducer vendaProducer;

    public VendaService(PagamentoService pagamentoService,
                        ClienteRepository clienteRepository,
                        ProdutoRepository produtoRepository,
                        VendaRepository vendaRepository,
                        VendaProducer vendaProducer) {
        this.pagamentoService = pagamentoService;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.vendaProducer = vendaProducer;
    }

    @Transactional
    public void confirmarVenda(Long idCliente, List<Long> idProdutos, String numeroCartao) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        if (clienteOpt.isEmpty()) {
            throw new DomainBusinessException("Cliente inexistente");
        }

        List<Optional<Produto>> produtosOptional = idProdutos.stream().map(produtoRepository::findById).collect(Collectors.toList());
        List<Produto> produtos = new ArrayList<>();

        produtosOptional.forEach(produto -> {
            if(produto.isEmpty()) {
                throw new DomainBusinessException("Algum produto não existe");
            }else{
                produtos.add(produto.get());
            }
        });

        Double total = produtos.stream().map(Produto::getPreco).reduce(0d, Double::sum);

        if(pagamentoService.confirmarPagamentoCartao(numeroCartao, total)){
            Venda venda = new Venda();
            venda.setCliente(clienteOpt.get());
            venda.setDesconto(0d);
            venda.setTotal(total);
            Pagamento pagamento = new Pagamento();
            pagamento.setSituacao(SituacaoPagamento.PAGO);
            pagamento.setValor(total);
            pagamento.setTipo(TipoPagamento.CARTAO);
            venda.setPagamento(pagamento);
            venda.setProdutos(produtos);
            venda = vendaRepository.save(venda);
            // envia para kafka
            NovaVendaDTO novaVendaDTO = new NovaVendaDTO();
            novaVendaDTO.setId(venda.getId());
            novaVendaDTO.setCodProdutos(produtos.stream().map(Produto::getId).collect(Collectors.toList()));
            novaVendaDTO.setTotal(total);
            novaVendaDTO.setCpfCliente(clienteOpt.get().getCpf());
            try {
                vendaProducer.novaVendaRealizada(novaVendaDTO);
            } catch (JsonProcessingException e) {
                throw new DomainBusinessException("Erro ao processar compra");
            }
        } else {
            throw new DomainBusinessException("Pagamento rejeitado");
        }

    }
}
