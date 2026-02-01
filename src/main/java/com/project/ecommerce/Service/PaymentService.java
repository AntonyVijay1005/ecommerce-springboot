package com.project.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Entity.Payment;
import com.project.ecommerce.Entity.UserModal;
import com.project.ecommerce.Repository.PaymentRepo;
import com.project.ecommerce.Repository.UserRepo;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private UserRepo userRepo;

    public String savePayment(Payment payment)
    {
        payment.setPaymentstatus("payment sucess");
         paymentRepo.save(payment);
         return "payment sucess";
    }

    public String makepayment(int userId,int orderId,double paymentprice)
    {
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setPaymentprice(paymentprice);
        payment.setPaymentstatus("success");

        UserModal user = userRepo.findById(userId).orElse(null);
        user.getPayments().add(payment);

        payment.setUser(user);
        paymentRepo.save(payment);
        
        return "payment success";
    }
    

}
