package com.javatechie.config;

import com.javatechie.agents.*;
import com.javatechie.listener.DebugAgentListener;
import com.javatechie.tool.OrderTools;
import com.javatechie.tool.RefundTool;
import com.javatechie.tool.ShippingTools;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.supervisor.SupervisorAgent;
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy;
import dev.langchain4j.model.chat.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    @Bean
    public OrderAgent orderAgent(
            ChatModel chatModel,
            OrderTools orderTools) {

        return AgenticServices
                .agentBuilder(OrderAgent.class)
                .chatModel(chatModel)
                .tools(orderTools)
                .outputKey("orderDetails")
                .build();
    }

    @Bean
    public RefundAgent refundAgent(
            ChatModel chatModel,
            RefundTool refundTool) {

        return AgenticServices
                .agentBuilder(RefundAgent.class)
                .chatModel(chatModel)
                .tools(refundTool)
                .outputKey("refundDecision")
                .build();
    }

    @Bean
    public ResponseAgent responseAgent(
            ChatModel chatModel) {

        return AgenticServices
                .agentBuilder(ResponseAgent.class)
                .chatModel(chatModel)
                .outputKey("finalResponse")
                .build();
    }

    @Bean
    public ShippingAgent shippingAgent(
            ChatModel chatModel,
            ShippingTools shippingTools) {

        return AgenticServices
                .agentBuilder(ShippingAgent.class)
                .chatModel(chatModel)
                .tools(shippingTools)
                .outputKey("shippingDetails")
                .build();
    }


//    @Bean
//    public WorkflowSupportAgent workflowSupportAgent(OrderAgent orderAgent,
//                                                     RefundAgent refundAgent,
//                                                     ResponseAgent responseAgent) {
//        return AgenticServices
//                .sequenceBuilder(WorkflowSupportAgent.class)
//                .subAgents(orderAgent, refundAgent, responseAgent)
//                .outputKey("finalResponse")
//                //.listener(new DebugAgentListener())
//                .build();
//
//    }

    @Bean
    public SupervisorAgent superVisorAgent(
            ChatModel chatModel,
            OrderAgent orderAgent,
            RefundAgent refundAgent,
            ResponseAgent responseAgent,
            ShippingAgent shippingAgent) {

        return AgenticServices
                .supervisorBuilder()
                .chatModel(chatModel)
                .subAgents(orderAgent,
                        refundAgent,
                        responseAgent,
                        shippingAgent)
                .responseStrategy(SupervisorResponseStrategy.LAST)
                .build();
    }
}
