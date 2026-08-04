package com.biblioteca.ejercicios_practica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CategoryRequest(
        @NotBlank
        @NotNull
        String name

) {
}
