package com.project.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.Entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
