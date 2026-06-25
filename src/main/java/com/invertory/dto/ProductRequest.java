package com.invertory.dto;

public record ProductRequest(
     String productId,
     String productName,
    //Stock Keeping Unit
     String skuCode,
     String brand,
     SupplierRequest supplierRequest,
     InventoryRequest inventoryRequest
     ){
}
