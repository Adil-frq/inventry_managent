package com.invertory.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactResponse(String street,
                              String city,
                              String country,
                              String pin,
                              String email,
                              String contactNo){
}
