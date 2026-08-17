package com.example.cliente_service.dto;


import com.example.cliente_service.model.Role;
import lombok.Data;
import lombok.Getter;


public record TokenDTO (
         String token,
         String name,
         Role role
        ){}



