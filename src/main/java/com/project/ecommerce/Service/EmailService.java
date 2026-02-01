package com.project.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Entity.Order;
import com.project.ecommerce.Entity.UserModal;
import com.project.ecommerce.Repository.UserRepo;

@Service
public class EmailService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String SenderEmail;

    public void SendEmail(Order order,int userId)
    {
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            UserModal user = userRepo.findById(userId).orElse(null);
            mail.setFrom(SenderEmail);
            mail.setTo(user.getUseremail());
            mail.setSubject("order details :");
            mail.setText("Your Order"+order.getOrdername()+" quantity "
            +order.getTotalquantity()+" total price "+order.getTotalprice()+" has been placed");
            javaMailSender.send(mail);
            System.out.print("Mail Send Succesfully.....");
        }
        catch(Exception e)
        {
            System.out.println("Mail not sent");
        }
        
    }

}
