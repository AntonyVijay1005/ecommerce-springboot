package com.project.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    
    
    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart,@RequestParam int userId,@RequestParam int productId)
    {
        return cartService.createCart(cart,userId,productId);
        
    }
    @GetMapping("/order/{cartId}")
    public ResponseEntity<String> cartOrder(@PathVariable int cartId)
    {
        return cartService.cartOrder(cartId); 
    }



    

}
