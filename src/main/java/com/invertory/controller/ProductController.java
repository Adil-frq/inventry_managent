package com.invertory.controller;

import com.invertory.dto.ProductRequest;
import com.invertory.dto.SupplierRequest;
import com.invertory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveProduct(@RequestBody ProductRequest request){
        productService.saveProduct(request);
    }
}
