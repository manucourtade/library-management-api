package com.biblioteca.ejercicios_practica.dto;

import com.biblioteca.ejercicios_practica.RolesEnum.Role;

public record LoginResponse(
        Long id,
        Role role,
        String username,
        String token
) {
}
