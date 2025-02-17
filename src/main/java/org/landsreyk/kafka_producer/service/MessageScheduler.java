package org.landsreyk.kafka_producer.service;

import lombok.RequiredArgsConstructor;
import org.landsreyk.kafka_producer.dto.OrderDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MessageScheduler {

    private final KafkaSender kafkaSender;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void sendMessage() {
        var orderDTO = OrderDTO.builder()
                .orderId(UUID.randomUUID())
                .orderDate(LocalDateTime.now())
                .customerName("Andrew Kozyrev")
                .product("Acer Nitro GR5")
                .quantity(2)
                .build();
        kafkaSender.sendMessage(orderDTO);
    }
}
