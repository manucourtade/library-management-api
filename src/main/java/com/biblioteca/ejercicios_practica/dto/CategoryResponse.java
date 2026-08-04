package com.biblioteca.ejercicios_practica.dto;
import java.util.List;

public record CategoryResponse(
        Long id,
        String name,
        List<BookResponse> books
) {
}
