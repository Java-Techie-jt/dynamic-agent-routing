package com.javatechie.repository;

import com.javatechie.model.RefundPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefundPolicyRepository
        extends JpaRepository<com.javatechie.model.RefundPolicy, Long> {

    Optional<RefundPolicy> findByOrderStatus(
            String orderStatus
    );
}