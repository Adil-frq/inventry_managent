package com.invertory.repository;

import com.invertory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
}
