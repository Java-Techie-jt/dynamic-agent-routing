package com.javatechie.service;

import com.javatechie.model.ShipmentTracking;
import com.javatechie.repository.ShipmentTrackingRepository;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private final ShipmentTrackingRepository repository;

    public ShippingService(ShipmentTrackingRepository repository) {
        this.repository = repository;
    }

    public ShipmentTracking getTracking(String orderId) {

        return repository.findByOrderId(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Shipment tracking not found for order: " + orderId
                        )
                );
    }


}