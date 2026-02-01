package com.project.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Entity.Order;
import com.project.ecommerce.Entity.Product;
import com.project.ecommerce.Entity.UserModal;
import com.project.ecommerce.Exceptions.CartNotFoundException;
import com.project.ecommerce.Exceptions.ProductNotFoundException;
import com.project.ecommerce.Exceptions.UserNotFoundException;
import com.project.ecommerce.Repository.CartRepo;
import com.project.ecommerce.Repository.ProductRepo;
import com.project.ecommerce.Repository.UserRepo;

@Service
public class CartService {

    @Autowired 
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderService orderService;

    public ResponseEntity<Cart> createCart(Cart cart,int userId,int productId)
    {
        Product product = productRepo.findById(productId).orElse(null);
        UserModal user = userRepo.findById(userId).orElse(null);
        if(user==null)
        {
            throw new UserNotFoundException("user not found");
        }
        else
        {
            if(product==null)
            {
                throw new ProductNotFoundException("product not found");
            }
            else
            {
                cart.setProductId(product.getProductId());
                cart.setCartname(product.getProductname());
                cart.setCartprice(product.getProductprice());
                cart.setProductImageUrl(product.getProductImageUrl());
                cart.setQuantity(cart.getQuantity());
                cart.setCarttotalprice(product.getProductprice()*cart.getQuantity());
                cart.setUser(user);
                user.getCarts().add(cart);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartRepo.save(cart))
                ;
            }
        }
       
    }

    public ResponseEntity<String> cartOrder(int cartId)
    {

        Cart cart = cartRepo.findById(cartId).orElse(null);
        if(cart==null)
        {
            throw new CartNotFoundException("cart not found");
        }
        else
        {
        Order order = new Order();
        order.setTotalquantity(cart.getQuantity());
        orderService.createOrder(order,cart.getUser().getUserId(),cart.getProductId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("cart added");
        
    }
        

        
    }

    

}
