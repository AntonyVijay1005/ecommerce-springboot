package com.project.ecommerce.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Entity.Admin;
import com.project.ecommerce.Entity.UserModal;
import com.project.ecommerce.Exceptions.UserNotFoundException;
import com.project.ecommerce.Repository.AdminRepo;
import com.project.ecommerce.Repository.UserRepo;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private UserRepo userRepo;

    
    @Override
    public UserDetails loadUserByUsername(String email)  {
        
        UserModal user = userRepo.findByUseremail(email);
        Admin admin = adminRepo.findByAdminemail(email);

        if(user == null && admin == null)
        {
            throw new UserNotFoundException("user not exists");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user!=null)
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(user.getUseremail(),user.getUserpassword(),authorities);
                
        }
        else
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(admin.getAdminemail(),admin.getAdminpassword(),authorities);
        }
        
        
    }

    

}
