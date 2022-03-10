package com.example.ecommerce.ecommerce.infraestructure.message.kafka;

import com.example.ecommerce.ecommerce.infraestructure.message.dto.NovaVendaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class VendaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${app.topic.nova-venda}")
    private String NOVA_VENDA_TOPIC;

    public VendaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void novaVendaRealizada(NovaVendaDTO dto) throws JsonProcessingException {
        var message = objectMapper.writeValueAsString(dto);
        kafkaTemplate.send(NOVA_VENDA_TOPIC, message);
    }
}
