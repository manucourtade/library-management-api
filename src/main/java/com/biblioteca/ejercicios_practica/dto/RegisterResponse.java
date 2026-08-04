package com.biblioteca.ejercicios_practica.dto;

import com.biblioteca.ejercicios_practica.RolesEnum.Role;

public record RegisterResponse(
        Long id,
        Role role,
        String username
) {
}
