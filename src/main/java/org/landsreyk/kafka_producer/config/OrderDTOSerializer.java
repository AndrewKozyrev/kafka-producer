package org.landsreyk.kafka_producer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Serializer;
import org.landsreyk.kafka_producer.dto.OrderDTO;

public class OrderDTOSerializer implements Serializer<OrderDTO> {

    private final ObjectMapper objectMapper;

    public OrderDTOSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public byte[] serialize(String topic, OrderDTO data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing OrderDTO", e);
        }
    }
}