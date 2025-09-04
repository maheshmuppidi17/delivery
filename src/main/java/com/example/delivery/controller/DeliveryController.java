package com.example.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.delivery.service.DeliveryService;
import com.example.delivery.model.Delivery;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/user")
    public ResponseEntity<List<Delivery>> getUserDeliveries(@RequestParam String userId) {
        return ResponseEntity.ok(deliveryService.getUserDeliveries(userId));
    }
}
