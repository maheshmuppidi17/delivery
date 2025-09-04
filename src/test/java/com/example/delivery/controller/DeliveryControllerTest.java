
package com.example.delivery.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.delivery.dto.ApiResponse;
import com.example.delivery.model.Delivery;
import com.example.delivery.response.dto.DeliveryResponseDTO;
import com.example.delivery.service.DeliveryService;

class DeliveryControllerTest {


    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private DeliveryController deliveryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


     @Test
     void testGetUserDeliveries_validUserId_returnsDeliveries() {
         String userId = "user123";

         DeliveryResponseDTO dto = new DeliveryResponseDTO();
         dto.setId("d1");
         dto.setUserId(userId);
         dto.setDeliveryDate(LocalDate.now());
         dto.setStatus("Delivered");

         List<DeliveryResponseDTO> deliveries = List.of(dto);

         when(deliveryService.getUserDeliveries(userId)).thenReturn(deliveries);

         ResponseEntity<?> response = deliveryController.getUserDeliveries(userId);

         assertEquals(200, response.getStatusCodeValue());
         assertTrue(response.getBody() instanceof ApiResponse);

         ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
         assertTrue(apiResponse.isSuccess());
         assertEquals("Deliveries fetched successfully", apiResponse.getMessage());
         assertEquals(deliveries, apiResponse.getData());

     }

     @Test
     void testGetUserDeliveries_emptyUserId_returnsBadRequest() {
         ResponseEntity<?> response = deliveryController.getUserDeliveries("   ");

         assertEquals(400, response.getStatusCodeValue());
         assertEquals("User ID must not be null or empty.", response.getBody());
     }

     @Test
     void testGetUserDeliveries_nullUserId_returnsBadRequest() {
         ResponseEntity<?> response = deliveryController.getUserDeliveries(null);

         assertEquals(400, response.getStatusCodeValue());
         assertEquals("User ID must not be null or empty.", response.getBody());
     }

     @Test
     void testGetUserDeliveries_noDeliveries_returnsEmptyList() {
         String userId = "user123";
         when(deliveryService.getUserDeliveries(userId)).thenReturn(List.of());

         ResponseEntity<?> response = deliveryController.getUserDeliveries(userId);

         assertEquals(200, response.getStatusCodeValue());
         assertTrue(response.getBody() instanceof ApiResponse);

         ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
         assertTrue(apiResponse.isSuccess());
         assertEquals("No deliveries found", apiResponse.getMessage());
         assertTrue(((List<?>) apiResponse.getData()).isEmpty());

     }


}
