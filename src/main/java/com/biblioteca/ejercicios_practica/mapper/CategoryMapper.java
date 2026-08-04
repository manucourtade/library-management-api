package com.biblioteca.ejercicios_practica.mapper;

import com.biblioteca.ejercicios_practica.dto.CategoryRequest;
import com.biblioteca.ejercicios_practica.dto.CategoryResponse;
import com.biblioteca.ejercicios_practica.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse (Category category);
    Category toCategory(CategoryRequest categoryRequest);
}
