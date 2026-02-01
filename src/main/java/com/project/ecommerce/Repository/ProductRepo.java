package com.project.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
