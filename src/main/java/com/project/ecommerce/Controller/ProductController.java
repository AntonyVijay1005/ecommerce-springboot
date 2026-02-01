package com.project.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.Entity.Product;
import com.project.ecommerce.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create/{adminId}")
    public ResponseEntity<Object> addProduct(@RequestBody Product product,@PathVariable int adminId)
    {
        return productService.addProduct(product,adminId);
        
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product)
    {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int productId)
    {
        return productService.deleteProduct(productId);
    }

}
