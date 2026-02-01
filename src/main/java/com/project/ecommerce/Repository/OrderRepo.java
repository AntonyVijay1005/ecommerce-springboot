package com.project.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.Entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{

}
