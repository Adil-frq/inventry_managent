package com.invertory.dto;

public record InventoryRequest(
        double purchasePrice,
        double sellingPrice,
        long quantity) {
}
