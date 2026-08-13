package com.example.cliente_service.Repository;

import com.example.cliente_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Long, Customer> {
    boolean existeByEmail(String email);
}
