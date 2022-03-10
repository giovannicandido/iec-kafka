package com.example.ecommerce.notafiscal.infraestructure.message.kafka;

import com.example.ecommerce.notafiscal.infraestructure.message.dto.NovaVendaDTO;
import com.example.ecommerce.notafiscal.service.NotaFiscalService;
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

    private final NotaFiscalService notaFiscalService;

    public NovaVendaConsumer(NotaFiscalService notaFiscalService) {
        this.notaFiscalService = notaFiscalService;
    }

    @KafkaListener(topics = "${app.topic.nova-venda}")
    @Transactional
    public void consume(@Payload String message, Acknowledgment ack) throws JsonProcessingException {

        log.info("Consumindo mensagem: " + message);
        var novaVendaDto = mapper.readValue(message, NovaVendaDTO.class);

        notaFiscalService.criarNota(novaVendaDto);

        ack.acknowledge();

    }
}
