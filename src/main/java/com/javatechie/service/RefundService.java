package com.javatechie.service;

import com.javatechie.model.RefundPolicy;
import com.javatechie.repository.RefundPolicyRepository;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    private RefundPolicyRepository repository;;

    public RefundService(RefundPolicyRepository repository) {
        this.repository = repository;
    }

    public RefundPolicy checkEligibility(String orderStatus) {
        System.out.println("\nREFUND SERVICE -> Checking status: " + orderStatus);
        return repository.findByOrderStatus(orderStatus)
                .orElseThrow(() -> new RuntimeException("Refund policy not found for: " + orderStatus));
    }
}
