package com.biblioteca.ejercicios_practica.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;

public record BookRequest(
        @NotNull
        @NotBlank
        @Size(max = 100)
        String title,

        @NotNull
        @NotBlank
        String isbn,

        @PositiveOrZero
        Integer stock,

        Long categoryId,
        List<Long> authorIds
) {
}
