package com.example.cliente_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustumerDTO {
    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "invalid email format")
    private String email;

    @NotBlank(message = "phone is required")
    private String phone;
}
