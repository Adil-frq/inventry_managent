package com.invertory.dto;

import com.invertory.entity.Contact;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SupplierRequest(

        @NotBlank(message = "Please enter the supplier name") String name,

        @NotBlank(message = "TRN cannot be empty")
        @Pattern(regexp = "^100\\d{12}$", message = "TRN must be exactly 15 digits and start with 100")
        String trn,

        ContactRequest contactRequest) {
}
