package com.invertory.service;

import com.invertory.dto.InventoryMapper;
import com.invertory.dto.ProductRequest;
import com.invertory.entity.Inventory;
import com.invertory.entity.Product;
import com.invertory.entity.Supplier;
import com.invertory.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Transactional
    public void saveInventory(ProductRequest request, Product product, Supplier supplier){
        Inventory inventory = InventoryMapper.toEntity(request);
        inventory.setProduct(product);
        inventory.setSupplier(supplier);

        Inventory savedInventory = inventoryRepository.save(inventory);
        log.info("Inventory saved successful {}", savedInventory.getInventoryId());
    }
}
