package com.example.delivery.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.delivery.dto.OrderEvent;
import com.example.delivery.model.Delivery;
import com.example.delivery.repo.DeliveryRepository;
import com.example.delivery.response.dto.DeliveryResponseDTO;

@Service
public class DeliveryService {
	private ModelMapper modelMapper;
    private final DeliveryRepository deliveryRepository;
    private static final Logger logger = 
    		LoggerFactory.getLogger(DeliveryService.class);
    public DeliveryService(DeliveryRepository deliveryRepository, ModelMapper modelMapper) {
        this.deliveryRepository = deliveryRepository;
        this.modelMapper = modelMapper;
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

    public List<DeliveryResponseDTO> getUserDeliveries(String userId) {
    	// Validate input
        if (userId == null || userId.trim().isEmpty()) {
        	logger.warn("Invalid userId provided");
        	throw new IllegalArgumentException("User ID must not be null or empty.");
        }
    	List<Delivery> deliveries =  deliveryRepository.findByUserId(userId);
        logger.debug("Deliveries found: {}", deliveries);

        return deliveries.stream()
        .map(delivery -> modelMapper.map(delivery, DeliveryResponseDTO.class))
        .collect(Collectors.toList());

    }
}