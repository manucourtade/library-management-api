package com.biblioteca.ejercicios_practica.dto;


import jakarta.validation.constraints.NotNull;

import java.util.List;


public record BookResponse(
        @NotNull
        Long id,
        String title,
        String isbn,
        Integer stock,
        Long categoryId,
        String categoryName,
        List<Long> authorIds,
        List<String> authorNames
) {
}
