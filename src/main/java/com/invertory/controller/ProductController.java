package com.invertory.controller;

import com.invertory.dto.ProductRequest;
import com.invertory.dto.SupplierRequest;
import com.invertory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody @Valid ProductRequest request){
        productService.saveProduct(request);
        return ResponseEntity.ok("Product saved successfully");
    }
}
