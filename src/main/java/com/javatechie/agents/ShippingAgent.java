package com.javatechie.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface ShippingAgent {

    @UserMessage("""
            Customer request:
            {{request}}

            Order details:
            {{orderDetails}}

            Extract the order ID and use the shipping tool
            to get current shipment information.

            Return only shipping-related details.
            """)
    @Agent(
            name = "shippingAgent",
            description = """
                    Use this agent for shipment tracking,
                    current location, carrier, delivery date
                    and shipping delay questions.
                    """,
            outputKey = "shippingDetails"
    )
    String checkShipping(
            @V("request") String request,
            @V("orderDetails") String orderDetails
    );
}