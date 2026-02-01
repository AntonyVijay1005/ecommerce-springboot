package com.project.ecommerce.GlobalHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.ecommerce.Exceptions.CartNotFoundException;
import com.project.ecommerce.Exceptions.OrderNotFoundException;
import com.project.ecommerce.Exceptions.ProductNotFoundException;
import com.project.ecommerce.Exceptions.ProductQuantityException;
import com.project.ecommerce.Exceptions.UserAlreadyExist;
import com.project.ecommerce.Exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<Object> handleAdminExist(UserAlreadyExist ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUpdate(UserNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProduct(ProductNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProductQuantityException.class)
    public ResponseEntity<Object> handleQuantity(ProductQuantityException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object>handleOrder(OrderNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Object>handleCart(CartNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

  

}
