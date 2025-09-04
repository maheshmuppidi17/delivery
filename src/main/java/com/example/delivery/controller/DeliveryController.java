package com.example.delivery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.delivery.service.DeliveryService;
import com.example.delivery.dto.ApiResponse;
import com.example.delivery.dto.OrderEvent;
import com.example.delivery.model.Delivery;
import com.example.delivery.response.dto.DeliveryResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;
    
    private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	
	@GetMapping("/user")
	public ResponseEntity<?> getUserDeliveries(@RequestParam String userId) {
		logger.info("Received request to fetch deliveries for userId: {}", userId);

		if (userId == null || userId.trim().isEmpty()) {
			logger.warn("Invalid userId provided: {}", userId);
			return ResponseEntity.badRequest().body("User ID must not be null or empty.");
		}

		List<DeliveryResponseDTO> deliveries = deliveryService.getUserDeliveries(userId);
		logger.debug("Deliveries fetched successfully for userId: {}", userId);
		if (deliveries == null || deliveries.isEmpty()) {
			logger.warn("No deliveries found for userId: {}", userId);
			return ResponseEntity.ok().body(new ApiResponse<>(true, "No deliveries found", deliveries));
		}
		
		   return ResponseEntity.ok().
				   body(new ApiResponse<>(true, "Deliveries fetched successfully", deliveries));
		
	}

    
    @PostMapping("/user")  //added for test (to add sample data) need to remove later
    public ResponseEntity<Delivery> addUserDeliveries(@RequestBody OrderEvent order) {
        return ResponseEntity.ok(deliveryService.createDelivery(order));
    }
}
