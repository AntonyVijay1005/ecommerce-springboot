package com.project.ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.Dto.LoginDto;
import com.project.ecommerce.Entity.UserModal;
import com.project.ecommerce.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserModal> getUsers()
    {
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(name="id") int userId)
    {
        return userService.getUser(userId);
    }
    @GetMapping("/cart/{userId}")
    public ResponseEntity<Object> getUserCart(@PathVariable int userId)
    {
        return userService.getUserCart(userId);
    }
    @GetMapping("/order/{userId}")
    public ResponseEntity<Object> getUserOrders(@PathVariable int userId)
    {
        return userService.getUserOrders(userId);
    }
     @GetMapping("/payment/{userId}")
    public ResponseEntity<Object> getUserPayments(@PathVariable int userId)
    {
        return userService.getUserPayments(userId);
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserModal user)
    {
        return userService.createUser(user);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserModal user)
    {
        return userService.updateUser(user);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId)
    {
        return userService.deleteUser(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginDto login)
    {
        return userService.loginUser(login);
    }
    

}
