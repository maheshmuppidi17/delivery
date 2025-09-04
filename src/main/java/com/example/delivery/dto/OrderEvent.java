package com.example.delivery.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderEvent {
    private String id;
    private String userId;
    private List<ProductEvent> products;
    private Double totalAmount;
    private LocalDateTime dateTime;
    private String status;

    // Getters and setters (must match e-commerce service)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public List<ProductEvent> getProducts() { return products; }
    public void setProducts(List<ProductEvent> products) { this.products = products; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}