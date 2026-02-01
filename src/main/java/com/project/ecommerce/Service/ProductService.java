package com.project.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Entity.Admin;
import com.project.ecommerce.Entity.Product;
import com.project.ecommerce.Exceptions.ProductNotFoundException;
import com.project.ecommerce.Exceptions.UserNotFoundException;
import com.project.ecommerce.Repository.AdminRepo;
import com.project.ecommerce.Repository.ProductRepo;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    @Autowired AdminRepo adminrepo;

    public ResponseEntity<Object> addProduct(Product product,int adminId)
    {
        Admin admin = adminrepo.findById(adminId).orElse(null);

        if(admin == null)
        {
            throw new UserNotFoundException("admin not found");
        }
        else
        {
        product.setAdmin(admin);
        admin.getProducts().add(product);
        return ResponseEntity.status(HttpStatus.FOUND).body(productRepo.save(product));
        
        }
        
        
    }
    public ResponseEntity<Object> updateProduct(Product product)
    {
       

        Product tempProduct = productRepo.findById(product.getProductId()).orElse(null);
        if(tempProduct!=null)
        {
        tempProduct.setProductname(product.getProductname());
        tempProduct.setProductprice(product.getProductprice());
        tempProduct.setProductstock(product.getProductstock());
        tempProduct.setProductImageUrl(product.getProductImageUrl());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productRepo.save(tempProduct));
        }
        else{
             throw new ProductNotFoundException("product not found");
        }
        
        
         
    }

    public ResponseEntity<Object> deleteProduct(int productId)
    {
        Product productFound  = productRepo.findById(productId).orElse(null);

        if(productFound!=null)
        {
            productRepo.deleteById(productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("product deleted");
        }
        else
        {
            throw new ProductNotFoundException("product not found");
        }
        
    }

}
