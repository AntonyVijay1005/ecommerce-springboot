package com.project.ecommerce.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int paymentId;
    private double paymentprice;
    private Date paymentDate = new Date();
    private String paymentstatus;

    private int orderId;
    @ManyToOne()
    @JsonBackReference("user-payment")
    @JoinColumn(name="userId")
    private UserModal user;

    public Payment() {
    }

    public int getPaymentId() {
        return paymentId;
    }

    public double getPaymentprice() {
        return paymentprice;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public UserModal getUser() {
        return user;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setPaymentprice(double paymentprice) {
        this.paymentprice = paymentprice;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public void setUser(UserModal user) {
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    

    

}
