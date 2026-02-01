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

import com.project.ecommerce.Dto.AdminDto;
import com.project.ecommerce.Entity.Admin;
import com.project.ecommerce.Dto.LoginDto;
import com.project.ecommerce.Entity.Product;
import com.project.ecommerce.Exceptions.UserAlreadyExist;
import com.project.ecommerce.Exceptions.UserNotFoundException;
import com.project.ecommerce.Repository.AdminRepo;

@Service
public class AdminService {

    @Autowired AdminRepo adminRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager auth_manager;
    @Autowired
    private JwtTokenService jwt_service;
    
    public List<Admin> getAdmins()
    {
        return adminRepo.findAll();
        
    }

    public ResponseEntity<Object> getAdmin(int adminId)
    {
        Admin admin = adminRepo.findById(adminId).orElse(null);
        if(admin!=null)
        {
            AdminDto adminDto = modelMapper.map(admin,AdminDto.class);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(adminDto);
        }
        else
        {
            throw new UserNotFoundException("user not found");
        }
    }
    public ResponseEntity<Object> getAdminProducts(int adminId)
    {
        Admin admin = adminRepo.findById(adminId).orElse(null);
        if(admin!=null)
        {
            List<Product> productItems = new ArrayList<>(admin.getProducts());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productItems);
        }
        else
        {
            throw new UserNotFoundException("user not found");
        }
    }
    public ResponseEntity<Object> createAdmin(Admin admin)
    {
        Admin admin_exist = adminRepo.findByAdminemail(admin.getAdminemail());
        if(admin_exist == null)
        {
            admin.setAdminpassword(new BCryptPasswordEncoder().encode(admin.getAdminpassword()));
            adminRepo.save(admin);
            AdminDto adminDto = modelMapper.map(admin,AdminDto.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(adminDto);
        }
        else
        {
            throw new UserAlreadyExist("Email Already Exists");
        }
    }

    public ResponseEntity<Object> updateAdmin(Admin admin)
    {
        Admin adminFound = adminRepo.findById(admin.getAdminId()).orElse(null);

        if(adminFound==null)
        {
           throw new UserNotFoundException("Admin not found");
        }
        else
        {
            adminRepo.save(admin);
            AdminDto adminDto = modelMapper.map(adminFound,AdminDto.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(adminDto);
        }  
    }
    
    public ResponseEntity<Object> deleteAdmin(int adminId)
    {
        Admin adminFound = adminRepo.findById(adminId).orElse(null);

        if(adminFound==null)
        {
                throw new UserNotFoundException("Admin Not found");
        }
        else
        {
            adminRepo.deleteById(adminId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("admin deleted");
        }  
    }

    public ResponseEntity<Object> adminLogin(LoginDto login)
    {
        Admin admin = adminRepo.findByAdminemail(login.getLoginemail());

        if(admin==null)
        {
            throw new UserNotFoundException("admin not found");
        }
        else
        {
            try{

            
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.getLoginemail(),login.getLoginpassword());
            Authentication authentication = auth_manager.authenticate(token);

            if(authentication.isAuthenticated())
            {
                String jwt_token  =jwt_service.Generate_Jwt_Token(login.getLoginemail());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("jwt is: "+jwt_token);
            }
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Admin email and password wrong");
        }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Admin email and password wrong");
        }
    }

 

}
