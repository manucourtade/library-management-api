package com.biblioteca.ejercicios_practica.service;

import com.biblioteca.ejercicios_practica.dto.CategoryRequest;
import com.biblioteca.ejercicios_practica.dto.CategoryResponse;
import com.biblioteca.ejercicios_practica.model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategoriesWithBooks();
    Category getCategoryEntityById(Long id);
    CategoryResponse getCategoryById(Long id);
    CategoryResponse createCategory (CategoryRequest category);
    CategoryResponse updateCategory (Long id, CategoryRequest categoryRequest);
    void deleteCategory(Long id);
}
