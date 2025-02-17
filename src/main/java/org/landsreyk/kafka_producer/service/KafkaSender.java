package org.landsreyk.kafka_producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.landsreyk.kafka_producer.dto.OrderDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaSender {

    private static final String TOPIC = "test";

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public void sendMessage(OrderDTO orderDTO) {
        log.info(">> sending order: {}", orderDTO);
        kafkaTemplate.send(TOPIC, orderDTO)
                .whenComplete((sendResult, ex) -> {
                    if (ex != null) {
                        log.error(">> error sending order: {}", orderDTO, ex);
                    } else {
                        log.info(">> order sent successfully: {}", sendResult.getProducerRecord().value());
                    }
                });
    }
}