package com.biblioteca.ejercicios_practica.securityConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); // 1. leo el header

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // 2. no hay token → sigo sin autenticar
            return; // corto acá, no ejecuto nada más de este método
        }

        String token = authHeader.substring(7); // 3. saco "Bearer " (7 caracteres) y me quedo con el token puro
        String username = jwtService.extractUsername(token); // 4. leo el username del token

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 5. hay username Y todavía nadie autenticó este request
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // 6. busco el usuario

            if (jwtService.isTokenValid(token, userDetails)) { // 7. valido el token
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken); // 8. autentico
            }
        }

        filterChain.doFilter(request, response); // 9. sigo la cadena de filtros
    }
}
