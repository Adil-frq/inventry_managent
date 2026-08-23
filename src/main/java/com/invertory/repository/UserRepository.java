package com.invertory.repository;

import com.invertory.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByUserName(String name);

    boolean existsByEmail(String email);
}
