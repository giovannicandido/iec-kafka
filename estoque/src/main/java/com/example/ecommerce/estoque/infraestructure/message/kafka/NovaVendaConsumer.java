package com.example.ecommerce.estoque.infraestructure.message.kafka;

import com.example.ecommerce.estoque.infraestructure.message.dto.NovaVendaDTO;
import com.example.ecommerce.estoque.service.EstoqueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class NovaVendaConsumer {
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(NovaVendaConsumer.class);

    private final EstoqueService estoqueService;

    public NovaVendaConsumer(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @KafkaListener(topics = "${app.topic.nova-venda}")
    @Transactional
    public void consume(@Payload String message, Acknowledgment ack) throws JsonProcessingException {

        log.info("Consumindo mensagem: " + message);
        var novaVendaDto = mapper.readValue(message, NovaVendaDTO.class);

        novaVendaDto.getCodProdutos().forEach(codProduto -> {
            estoqueService.retirarEstoque(codProduto, 1);
        });

        ack.acknowledge();

    }
}
