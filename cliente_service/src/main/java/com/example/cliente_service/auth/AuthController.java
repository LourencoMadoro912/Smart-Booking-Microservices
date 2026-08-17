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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
private final AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }

}
