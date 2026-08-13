package com.example.cliente_service.controller;

import com.example.cliente_service.Service.CustomerService;
import com.example.cliente_service.dto.CustumerDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("custumer")
public class CustomerController {
    private  final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping
    public ResponseEntity<CustumerDTO> createCustomer(@RequestBody @Valid CustumerDTO custumerDTO){
      return ResponseEntity.ok(customerService.createCustomer(custumerDTO));
    }

    @GetMapping("/{custumerId}")
    public ResponseEntity<List<CustumerDTO>> getCustomerById(@PathVariable Long custumerId){
        return ResponseEntity.ok(customerService.getCustomer(custumerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustumerDTO> updateById(@PathVariable Long id,@RequestBody @Valid CustumerDTO custumerDTO){
        return ResponseEntity.ok(customerService.updateCustomer(id,custumerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        customerService.deleteCustomer(id);
       return ResponseEntity.noContent().build();
    }
}
