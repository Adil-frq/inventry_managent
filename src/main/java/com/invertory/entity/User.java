package com.invertory.entity;

import com.invertory.enums.Role;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    //unique
    private String userName;
    private String password;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

}
