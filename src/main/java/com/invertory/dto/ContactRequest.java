package com.invertory.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactRequest(String street,
                             String city,
                             String country,
                             String pin,
                             @Email(message = "Please enter a valid email")
                             String email,
                             @NotBlank(message = "contact number should not be empty")
                             String contactNo) {
}
