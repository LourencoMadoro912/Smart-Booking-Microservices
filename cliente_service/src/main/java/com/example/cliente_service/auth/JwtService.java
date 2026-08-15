package com.example.cliente_service.auth;

import com.example.cliente_service.config.ConfigJwtYml;
import com.example.cliente_service.model.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
@Data
public class JwtService {
    private final ConfigJwtYml configJwtYml;

    ///genearte jwt(token)
    public String gererateJwt(Customer customer){


        return Jwts.builder()
                .setSubject(Long.toString(customer.getId()))
                .claim("email",customer.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+configJwtYml.getEXPIRATION()))
                .signWith(SignatureAlgorithm.HS256, configJwtYml.getSECRET())
                .compact();
    }

    //extract main
    public <T> T extractClaim(String  token, Function<Claims,T> function){
        final Claims claims=Jwts.parserBuilder()
                .setSigningKey(configJwtYml.getSECRET())
                .build()
                .parseClaimsJws(token)
                .getBody();
          return function.apply(claims);
    }

    //extract Email
    public String extractEmail(String token){
        return  extractClaim(token, claims -> claims.get("email").toString());
    }

    public Long extractUserId(String token){
        return Long.parseLong(extractClaim(token, Claims::getSubject));
    }

    public Date extractExpiration(String token){
        return  extractClaim(token,Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserPrincipal userPrincipal){
        final Long userId=extractUserId(token);
        final String email=extractEmail(token);

        return (userId.equals(userPrincipal.getId())
                && email.equals(userPrincipal.getEmail())
                && !isTokenExpired(token));
    }

}
