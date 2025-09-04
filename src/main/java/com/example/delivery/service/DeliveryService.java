package com.example.delivery.service;

import org.springframework.stereotype.Service;
import com.example.delivery.model.Delivery;
import com.example.delivery.repo.DeliveryRepository;
import com.example.delivery.dto.OrderEvent;
import com.example.delivery.dto.ProductEvent;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery createDelivery(OrderEvent order) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(order.getId());
        delivery.setUserId(order.getUserId());
        delivery.setProducts(order.getProducts()); // This now expects List<ProductEvent>
        delivery.setTotalAmount(order.getTotalAmount());
        delivery.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8));
        delivery.setDeliveryDate(LocalDate.now().plusDays(5));
        delivery.setStatus("PROCESSING");

        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getUserDeliveries(String userId) {
        return deliveryRepository.findByUserId(userId);
    }
}