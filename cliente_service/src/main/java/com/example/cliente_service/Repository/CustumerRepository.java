package com.example.cliente_service.Repository;

import com.example.cliente_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustumerRepository extends JpaRepository<Long, Customer> {
    boolean existeByEmail(String email);
}
