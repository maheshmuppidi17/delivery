package com.example.delivery.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.serializer.DeserializationException;

import com.example.delivery.service.DeliveryService;
import com.example.delivery.dto.OrderEvent;

@Service
public class KafkaConsumer {

    private final DeliveryService deliveryService;

    public KafkaConsumer(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @KafkaListener(topics = "order-events", groupId = "delivery-group")
    public void consumeOrderEvent(OrderEvent orderEvent,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            deliveryService.createDelivery(orderEvent);
            System.out.println("Delivery created for order: " + orderEvent.getId());
        } catch (Exception e) {
            System.err.println("Error processing message from topic: " + topic + ", offset: " + offset);
            System.err.println("Error: " + e.getMessage());
            // Don't throw exception to avoid infinite retry loop
        }
    }
}