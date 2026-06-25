package com.invertory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;
    //after purchasing the product I want to add into inventory
    @ManyToOne
    private Product product;
    @ManyToOne
    private Supplier supplier;

    private double purchasePrice;
    private double sellingPrice;
    private long quantity;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
