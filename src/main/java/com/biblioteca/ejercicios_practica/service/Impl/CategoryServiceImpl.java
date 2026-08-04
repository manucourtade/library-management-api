package com.biblioteca.ejercicios_practica.service.Impl;

import com.biblioteca.ejercicios_practica.dto.CategoryRequest;
import com.biblioteca.ejercicios_practica.dto.CategoryResponse;
import com.biblioteca.ejercicios_practica.exception.ResourceAlreadyExistsException;
import com.biblioteca.ejercicios_practica.exception.ResourceNotFoundException;
import com.biblioteca.ejercicios_practica.mapper.CategoryMapper;
import com.biblioteca.ejercicios_practica.model.Category;
import com.biblioteca.ejercicios_practica.repository.CategoryRepository;
import com.biblioteca.ejercicios_practica.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private  final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategoriesWithBooks() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryEntityById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id)
        );
        return categoryMapper.toResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse createCategory (CategoryRequest categoryRequest) {
        if (categoryRepository.existsByName(categoryRequest.name())) {
            throw new ResourceAlreadyExistsException("Category", "name", categoryRequest.name());
        }
    Category category = categoryMapper.toCategory(categoryRequest);
    Category savedCategory = categoryRepository.save(category);
    log.info("Category created: {}", savedCategory.getId());
    return categoryMapper.toResponse(savedCategory);

    }

    @Override
    @Transactional
    public CategoryResponse updateCategory (Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id)
        );
        String nameOld = category.getName();
        category.setName(categoryRequest.name());
        Category saveCategory = categoryRepository.save(category);
        log.info("Category update name: {} -> {}", nameOld, saveCategory.getName());
        return categoryMapper.toResponse(saveCategory);
    }

    @Override
    @Transactional
    public void deleteCategory (Long id) {
        if (! categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category", "id", id);
        }
        categoryRepository.deleteById(id);
    }
}
