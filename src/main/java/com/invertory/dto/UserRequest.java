package com.invertory.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotNull(message = "First name should not be null")
        @NotBlank(message = "Username should not be empty")
        @Size(min =  3,  max = 15, message = "Username must be between 3 character to 15 character")
        String firstName, // required
        String lastName, // not required
        @Email(message = "Please enter a valid email")
        String email, //required
        @NotBlank(message = "Please enter username")
        @Size(min = 3, max = 20, message = "Username must be between 3 character to 15 character")
        String username,
        @Size(min = 8, message = "Password must be min 8 character")
        String password
        //String ROLE role, It will be provided by admin
) {
}
