package com.project.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.Entity.Order;
import com.project.ecommerce.Entity.Product;
import com.project.ecommerce.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody Order order,@RequestParam int userId,@RequestParam int productId)
    {
        return orderService.createOrder(order,userId,productId);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable int orderId)
    {
        return orderService.deleteOrder(orderId);
    }

}
