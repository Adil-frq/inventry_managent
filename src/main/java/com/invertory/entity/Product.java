package com.invertory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    private String productId;
    private String productName;
    //Stock Keeping Unit
    private String skuCode;
    private String brand;
    //unit_of_measure KG , BOX, PCS
    //category
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    //created or updated by username

    @ManyToMany
    @JoinTable(
            name="product_supplier",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns =@JoinColumn(name= "supplier_id")
    )
    //@JoinColumn(name="supplier_id")
    private List<Supplier> supplier;
}
