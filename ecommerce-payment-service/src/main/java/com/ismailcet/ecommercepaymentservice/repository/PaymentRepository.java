package com.ismailcet.ecommercepaymentservice.repository;

import com.ismailcet.ecommercepaymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
