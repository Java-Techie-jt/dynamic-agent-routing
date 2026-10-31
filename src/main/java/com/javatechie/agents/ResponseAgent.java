package com.javatechie.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface ResponseAgent {

    @UserMessage("""
        Original customer request:
        {{request}}

        Order details:
        {{orderDetails}}

        Shipping details:
        {{shippingDetails}}

        Refund decision:
        {{refundDecision}}

        Answer the exact user question.

        Important:
        - Include every detail explicitly requested by the user.
        - If the user asks for a tracking number, include the tracking number.
        - If information is not available, do not invent it.
        - Do not mention refund information unless the user asked about refunds.
        """)
    @Agent(
            outputKey = "finalResponse",
            description = """
            Creates the final customer response using available
            order, shipping and refund information.
            """
    )
    String createResponse(
            @V("request") String request,
            @V("orderDetails") String orderDetails,
            @V("shippingDetails") String shippingDetails,
            @V("refundDecision") String refundDecision
    );
}
