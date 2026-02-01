package com.project.ecommerce.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dto.LoginDto;
import com.project.ecommerce.Dto.UserDto;
import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Entity.Order;
import com.project.ecommerce.Entity.Payment;
import com.project.ecommerce.Entity.UserModal;
import com.project.ecommerce.Exceptions.UserAlreadyExist;
import com.project.ecommerce.Exceptions.UserNotFoundException;
import com.project.ecommerce.Repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthenticationManager auth_manager;

    @Autowired
    private JwtTokenService jwt_service;

    public List<UserModal> getUsers()
    {
        return userRepo.findAll();
    }

    public ResponseEntity<Object> getUser(int userId)
    {
        UserModal user =  userRepo.findById(userId).orElse(null);

        if(user!=null)
        {
         UserDto userDto = modelMapper.map(user,UserDto.class);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDto);
        }
        else
        {
            throw new UserNotFoundException("user not found");
        }
        
    }

    public ResponseEntity<Object> getUserCart(int userId)
    {
        UserModal user = userRepo.findById(userId).orElse(null);
        if(user==null)
        {
            throw new UserNotFoundException("user not found");
        }
        else
        {
            List<Cart> cartItems = new ArrayList<>(user.getCarts());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartItems);
        }
    }

    public ResponseEntity<Object> getUserOrders(int userId)
    {
        UserModal user = userRepo.findById(userId).orElse(null);
        if(user==null)
        {
            throw new UserNotFoundException("user not found");
        }
        else
        {
            List<Order> orderItems = new ArrayList<>(user.getOrders());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderItems);
        }
    }
    public ResponseEntity<Object> getUserPayments(int userId)
    {
        UserModal user = userRepo.findById(userId).orElse(null);
        if(user==null)
        {
            throw new UserNotFoundException("user not found");
        }
        else
        {
            List<Payment> payments = new ArrayList<>(user.getPayments());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(payments);
        }
    }
    public ResponseEntity<Object> createUser(UserModal user)
    {
        UserModal userFound = userRepo.findByUseremail(user.getUseremail());

        if(userFound !=null)
        {
            throw new UserAlreadyExist("user already Exists");
        }
        else
        {
            user.setUserpassword(new BCryptPasswordEncoder().encode(user.getUserpassword()));
            userRepo.save(user);
            UserDto userdto = modelMapper.map(user , UserDto.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(userdto);
        }
        
    
    }
    public ResponseEntity<Object> updateUser(UserModal user)
    {
        UserModal userFound = userRepo.findById(user.getUserId()).orElse(null);

        if(userFound!=null)
        {
            userRepo.save(user);
            UserDto userdto = modelMapper.map(user , UserDto.class);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userdto);
        }
        else
        {
            throw new UserNotFoundException("user not found");
        }
    }
    public ResponseEntity<String> deleteUser(int userId)
    {
        UserModal userFound = userRepo.findById(userId).orElse(null);
        
        if(userFound!=null)
        {
            userRepo.deleteById(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("user deleted");
        }
        else
        {
            throw new UserNotFoundException("user not found");
        }
    }

    public ResponseEntity<Object> loginUser(LoginDto login)
    {
        UserModal user = userRepo.findByUseremail(login.getLoginemail());

        if(user==null)
        {
            throw new UserNotFoundException("user not exists");
        }
        else
        {
            try{
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.getLoginemail(),login.getLoginpassword());
            Authentication authentication = auth_manager.authenticate(token);
            if(authentication.isAuthenticated())
            {
                String jwt_token=jwt_service.Generate_Jwt_Token(login.getLoginemail());
                System.out.println(jwt_token);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("jwt token is    "+jwt_token);
            }
        }
        catch(Exception e)
        {
                 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("username and pwd is wrong");
        }
         return ResponseEntity.status(HttpStatus.FORBIDDEN).body("username and pwd is wrong");
           
        }
    }


}
