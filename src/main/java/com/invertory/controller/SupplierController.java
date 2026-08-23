package com.invertory.controller;

import com.invertory.dto.SupplierRequest;
import com.invertory.dto.SupplierResponse;
import com.invertory.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    public ResponseEntity<?> saveSupplier(@Valid @RequestBody SupplierRequest request){
        SupplierResponse response = supplierService.saveSupplier(request);
        return ResponseEntity.ok(response);
    }
}
