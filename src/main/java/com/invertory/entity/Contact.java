package com.invertory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Contact {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="contact_id")
    private String contactId;
    private String street;
    private String city;
    private String pin;
    private String country;
    private String email;
    private String contactNo;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

//    @OneToOne
//    @JoinColumn(name="supplier_id", referencedColumnName = "supplier_id")
//    Supplier supplier;
}
