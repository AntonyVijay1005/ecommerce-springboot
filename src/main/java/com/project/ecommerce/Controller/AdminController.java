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

import com.project.ecommerce.Entity.Admin;
import com.project.ecommerce.Dto.LoginDto;
import com.project.ecommerce.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public List<Admin> getAdmins()
    {
        return adminService.getAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAdmin(@PathVariable(name="id") int adminId)
    {
        return adminService.getAdmin(adminId);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getAdminProducts(@PathVariable(name="id") int adminId)
    {
        return adminService.getAdminProducts(adminId);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createAdmin(@RequestBody Admin admin)
    {
        return adminService.createAdmin(admin);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateAdmin(@RequestBody Admin admin)
    {
        return adminService.updateAdmin(admin);
    }

    @DeleteMapping("/delete/{adminId}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable int adminId)
    {
        return adminService.deleteAdmin(adminId);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Object> adminLogin(@RequestBody LoginDto login)
    {
        return adminService.adminLogin(login);
    }

}
