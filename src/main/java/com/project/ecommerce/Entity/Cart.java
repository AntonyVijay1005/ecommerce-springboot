package com.project.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cartId;
    private String cartname;
    private double cartprice;
    private double carttotalprice;
    private int quantity;
    private int productId;
    private String productImageUrl;
    
    @ManyToOne()
    @JoinColumn(name="userId")
    @JsonBackReference("user-cart")
    private UserModal user;
    public Cart()
    {

    }
    public int getCartId() {
        return cartId;
    }
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
    public String getCartname() {
        return cartname;
    }
    public void setCartname(String cartname) {
        this.cartname = cartname;
    }
    public double getCartprice() {
        return cartprice;
    }
    public void setCartprice(double cartprice) {
        this.cartprice = cartprice;
    }
    public double getCarttotalprice() {
        return carttotalprice;
    }
    public void setCarttotalprice(double carttotalprice) {
        this.carttotalprice = carttotalprice;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public UserModal getUser() {
        return user;
    }
    public void setUser(UserModal user) {
        this.user = user;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    

}
