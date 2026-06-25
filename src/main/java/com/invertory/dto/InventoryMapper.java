package com.invertory.dto;

import com.invertory.entity.Inventory;
import com.invertory.entity.Product;

public interface InventoryMapper {
    public  static <T> Inventory toEntity(T t){
        Inventory inventory = new Inventory();
        if(t instanceof InventoryRequest request){

            inventory.setQuantity(request.quantity());
            inventory.setPurchasePrice(request.purchasePrice());
            inventory.setSellingPrice(request.sellingPrice());
        }
        return inventory;
    }
}
