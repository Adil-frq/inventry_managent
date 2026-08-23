package com.invertory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
//     String productId,
     @NotBlank(message = "Please enter the product name")
     @NotNull(message = "Product name should not be null")
     String productName,
    //Stock Keeping Unit
     @NotBlank(message = "Please enter the sku code")
     @NotEmpty(message = "Sku code should not be empty")
     String skuCode,

     String brand,
     //SupplierRequest supplierRequest
     @NotBlank(message = "Please enter the supplier name")
     @NotNull(message = "Supplier name should not be blank")
     @NotEmpty(message = "Supplier name should not be empty")
     String supplierName,
     String trn
     ){
}
