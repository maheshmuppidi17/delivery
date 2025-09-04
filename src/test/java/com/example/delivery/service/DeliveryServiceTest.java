
package com.example.delivery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.example.delivery.model.Delivery;
import com.example.delivery.repo.DeliveryRepository;
import com.example.delivery.response.dto.DeliveryResponseDTO;


class DeliveryServiceTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryService deliveryService;
    

    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks manually
    }


    @Test
    void testGetUserDeliveries_modelMapperIsMocked() {
        String userId = "user123";

        Delivery delivery = new Delivery();
        delivery.setId("d1");
        delivery.setUserId(userId);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setStatus("Delivered");

        DeliveryResponseDTO dto = new DeliveryResponseDTO();
        dto.setId("d1");
        dto.setUserId(userId);
        dto.setDeliveryDate(delivery.getDeliveryDate());
        dto.setStatus("Delivered");

        when(deliveryRepository.findByUserId(userId)).thenReturn(List.of(delivery));
        when(modelMapper.map(delivery, DeliveryResponseDTO.class)).thenReturn(dto);

        List<DeliveryResponseDTO> result = deliveryService.getUserDeliveries(userId);

        assertEquals(1, result.size());
        assertEquals("d1", result.get(0).getId());
       
    }


    @Test
    void testGetUserDeliveries_nullUserId_throwsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryService.getUserDeliveries(null);
        });
        assertEquals("User ID must not be null or empty.", exception.getMessage());
    }

    @Test
    void testGetUserDeliveries_emptyUserId_throwsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryService.getUserDeliveries("   ");
        });
        assertEquals("User ID must not be null or empty.", exception.getMessage());
    }
}


