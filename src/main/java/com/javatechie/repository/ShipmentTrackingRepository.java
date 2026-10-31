package com.javatechie.repository;

import com.javatechie.model.ShipmentTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipmentTrackingRepository
        extends JpaRepository<com.javatechie.model.ShipmentTracking, Long> {

    Optional<ShipmentTracking> findByOrderId(String orderId);
}