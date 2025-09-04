package com.example.delivery.model;

import com.example.delivery.dto.ProductEvent; // Import the DTO
import java.time.LocalDate;
import java.util.List;

public class Delivery {
    private String id;
    private String orderId;
    private String userId;
    private List<ProductEvent> products; // Change from Product to ProductEvent
    private Double totalAmount;
    private String invoiceNumber;
    private LocalDate deliveryDate;
    private String status;

    // Setters
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setProducts(List<ProductEvent> products) { this.products = products; } // Updated parameter type
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
    public void setStatus(String status) { this.status = status; }

    // Getters
    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public String getUserId() { return userId; }
    public List<ProductEvent> getProducts() { return products; } // Updated return type
    public Double getTotalAmount() { return totalAmount; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public String getStatus() { return status; }
}