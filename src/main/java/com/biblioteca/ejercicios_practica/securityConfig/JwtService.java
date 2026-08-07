package com.biblioteca.ejercicios_practica.securityConfig;

import com.biblioteca.ejercicios_practica.model.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);
    Claims extractToken (String token);
    <T> T extractClaim(String token, String field, Class<T> type);

}
