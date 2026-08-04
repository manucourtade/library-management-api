package com.biblioteca.ejercicios_practica.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthorRequest(
        @NotBlank
        String name
) {
}
