package com.example.cliente_service.Service;

import com.example.cliente_service.Repository.CustomerRepository;
import com.example.cliente_service.dto.CustumerDTO;
import com.example.cliente_service.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


public CustumerDTO createCustomer(CustumerDTO custumerDTO){
        String email=custumerDTO.getEmail().toLowerCase().trim();

        if (customerRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("Email already in use");
        }

        Customer customer=Customer.builder()
                .name(custumerDTO.getName())
                .email(custumerDTO.getEmail())
                .phone(custumerDTO.getPhone())
                .build();

        Customer save=customerRepository.save(customer);

        return toDTO(save);
}


//Get custumer
public List<CustumerDTO> getCustomer(Long id){
       return customerRepository.findById(id)
               .stream()
               .map(this::toDTO)
               .collect(Collectors.toList());
}

public CustumerDTO updateCustomer( Long id,CustumerDTO custumerDTO){
        Customer customer=customerRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Custumer not found")
        );

        CustumerDTO dto=new CustumerDTO();

        if (custumerDTO.getEmail()!= null){
            customer.setEmail(custumerDTO.getEmail());
        }

        if (custumerDTO.getName()!=null){
            customer.setName(custumerDTO.getName());
        }

        if (custumerDTO.getPhone()!=null){
            customer.setPhone(custumerDTO.getPhone());
        }

        customer=customerRepository.save(customer);

        return toDTO(customer);
}

public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
}

public CustumerDTO toDTO(Customer customer){
        CustumerDTO dto=new CustumerDTO();
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());

        return dto;
}

}
