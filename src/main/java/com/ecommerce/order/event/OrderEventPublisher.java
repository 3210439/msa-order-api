package com.ecommerce.order.event;

import com.ecommerce.order.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON 변환용
    private final String topicName = "order-event";

    public void publishOrderCreated(Order order) {
        log.info("Kafka 메시지 전송 시도: orderId={}", order.getId());
        try {
            OrderEvent event = new OrderEvent();
            event.setEventId(java.util.UUID.randomUUID().toString());
            event.setOrderId(order.getId());
            event.setUserId(order.getUserId().toString());
            event.setStatus(order.getStatus());
            event.setProductId(order.getProductId());
            event.setQuantity(order.getQuantity());
            String message = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(topicName, message)
                .addCallback(
                    result -> log.info("Message sent successfully to topic: {}, partition: {}",
                        topicName, Objects.requireNonNull(result).getRecordMetadata().partition()),
                    ex -> log.error("Failed to send message to topic: {}", topicName, ex)
                );
        } catch (Exception e) {
            log.error("Error publishing order created event", e);
            throw new RuntimeException("Failed to publish order event", e);
        }
    }
}