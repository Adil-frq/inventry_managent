package com.invertory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="supplier")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Supplier {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="supplier_id")
    private String supplierId;
    private String name;
    private String trn; // gst

    @OneToOne
    @JoinColumn(name="contact_id", referencedColumnName = "contact_id")
    private Contact contactId;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    //supplier can have multiple product
    //@ManyToMany(mappedBy = "supplier")
    //List<Product> productList;

}
