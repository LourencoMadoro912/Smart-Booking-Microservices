package com.example.cliente_service.auth;

import com.example.cliente_service.Repository.CustomerRepository;
import com.example.cliente_service.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer=customerRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("User not found with email: "+email)
        );
        return new UserPrincipal(customer);
    }
}
