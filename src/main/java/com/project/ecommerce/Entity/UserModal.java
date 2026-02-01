package com.project.ecommerce.Entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserModal {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String useremail;
    private String userpassword;
    private String address;
    private boolean user=true;

    @JsonManagedReference("user-orders")
    @OneToMany(mappedBy="user" ,cascade=CascadeType.ALL)
    private List<Order>orders;

    @JsonManagedReference("user-cart")
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Cart> carts;

    @JsonManagedReference("user-payment")
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<Payment> payments;

    public UserModal()
    {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Object findById(int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    

}
