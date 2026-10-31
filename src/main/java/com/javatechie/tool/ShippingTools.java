package com.javatechie.tool;

import com.javatechie.model.ShipmentTracking;
import com.javatechie.service.ShippingService;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class ShippingTools {

    private final ShippingService shippingService;

    public ShippingTools(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @Tool("Get current shipment tracking and delivery details for an order")
    public String trackShipment(String orderId) {

        ShipmentTracking tracking =
                shippingService.getTracking(orderId);

        System.out.println(
                "\n>>> SHIPPING TOOL CALLED -> "
                        + orderId
        );

        return """
                Order ID: %s
                Carrier: %s
                Tracking Number: %s
                Shipment Status: %s
                Current Location: %s
                Estimated Delivery: %s
                """
                .formatted(
                        tracking.getOrderId(),
                        tracking.getCarrier(),
                        tracking.getTrackingNumber(),
                        tracking.getShipmentStatus(),
                        tracking.getCurrentLocation(),
                        tracking.getEstimatedDelivery()
                );
    }
}