package com.invertory.dto;

public record SupplierResponse(
        String supplierId,
        String name,
        String trn,
        ContactResponse contactResponse
) {
}
