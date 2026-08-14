package com.example.cliente_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="jwt")
@Data
public class ConfigJwtYml {
    private final String SECRET;
    private final long EXPIRATION;
}
