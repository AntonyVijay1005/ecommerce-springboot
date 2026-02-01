package com.project.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Entity.Order;
import com.project.ecommerce.Entity.Payment;
import com.project.ecommerce.Entity.Product;
import com.project.ecommerce.Entity.UserModal;
import com.project.ecommerce.Exceptions.OrderNotFoundException;
import com.project.ecommerce.Exceptions.ProductNotFoundException;
import com.project.ecommerce.Exceptions.ProductQuantityException;
import com.project.ecommerce.Exceptions.UserNotFoundException;
import com.project.ecommerce.Repository.OrderRepo;
import com.project.ecommerce.Repository.PaymentRepo;
import com.project.ecommerce.Repository.ProductRepo;
import com.project.ecommerce.Repository.UserRepo;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private EmailService emailService;
    @Autowired 
    private PaymentService paymentService;

    public ResponseEntity<Object> createOrder(Order order,int userId,int productId)
    {
     
        Product product = productRepo.findById(productId).orElse(null);
        UserModal user  = userRepo.findById(userId).orElse(null);
        
        if(product==null)
        {
            throw new ProductNotFoundException("product not found");
        }
        else 
        {
            if(order.getTotalquantity()>product.getProductstock())
            {
                throw new ProductQuantityException("no stock available");
            }
            else
            {
                if(user==null)
                {
                    throw new UserNotFoundException("user not found");
                }
                else
                {
                    order.setProductId(product.getProductId());
                    order.setOrdername(product.getProductname());
                    order.setOrderprice(product.getProductprice());
                    order.setTotalquantity(order.getTotalquantity());
                    order.setProductImageUrl(product.getProductImageUrl());
                    order.setTotalprice(order.getTotalquantity()*product.getProductprice());
                    
                    order.setUser(user);
                    user.getOrders().add(order);
                    productRepo.save(product);
                    

                    paymentService.makepayment(userId,order.getOrderId(),order.getTotalprice());
                    product.setProductstock(product.getProductstock()-order.getTotalquantity());
                   
                    Order orders=orderRepo.save(order);
                    emailService.SendEmail(orders, userId);

                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(orders);
                    
                }
            }
        }
        
    }

    public ResponseEntity<Object> deleteOrder(int orderId)
    {
        Order order = orderRepo.findById(orderId).orElse(null);
        
        if(order==null)
        {
            throw new OrderNotFoundException("order not found");
        }
        else
        {
            int productId = order.getProductId();
            Product product = productRepo.findById(productId).orElse(null);
            product.setProductstock(order.getTotalquantity()+product.getProductstock());
            
            Payment payment = paymentRepo.findByOrderId(orderId);
            payment.setPaymentstatus("Amount will be refunded");
            paymentRepo.save(payment);
            
            orderRepo.deleteById(orderId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("order deleted");
        }
       
      
        
        
     
    }

}

