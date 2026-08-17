package com.example.cliente_service.auth;

import com.example.cliente_service.Repository.CustomerRepository;
import com.example.cliente_service.dto.AuthDTO;
import com.example.cliente_service.dto.TokenDTO;
import com.example.cliente_service.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class AuthService {
    private  final AuthenticationManager authenticationManager;
    private  final CustomerRepository customerRepository;
    private final  JwtService jwtService;


    public TokenDTO login(AuthDTO dto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        Customer customer = customerRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("user not found"));

        String acessToken = jwtService.gererateJwt(customer);

        return new TokenDTO(acessToken, customer.getName(), customer.getRole());
    }


}
