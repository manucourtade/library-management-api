package com.biblioteca.ejercicios_practica.controller;

import com.biblioteca.ejercicios_practica.dto.CategoryRequest;
import com.biblioteca.ejercicios_practica.dto.CategoryResponse;
import com.biblioteca.ejercicios_practica.model.Category;
import com.biblioteca.ejercicios_practica.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private  final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCategoriesWithBooks() {
        return categoryService.getAllCategoriesWithBooks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategoryById (@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory (@Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse updateCategory (@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(id, categoryRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteCategory (@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }


}
