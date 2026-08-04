package com.biblioteca.ejercicios_practica.dto;


import jakarta.validation.constraints.NotNull;


public record BookResponse(
        @NotNull
        Long id,
        String title,
        String isbn,
        Integer stock
) {
}
