package com.example.delivery.repo;

import com.example.delivery.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    List<Delivery> findByUserId(String userId);
}