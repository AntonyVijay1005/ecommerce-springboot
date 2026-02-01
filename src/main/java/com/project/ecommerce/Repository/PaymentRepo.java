package com.project.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.Entity.Payment;


public interface PaymentRepo extends JpaRepository<Payment, Integer>{

    Payment  findByOrderId(int orderId);
}
