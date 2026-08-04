package com.biblioteca.ejercicios_practica.repository;

import com.biblioteca.ejercicios_practica.model.Book;
import com.biblioteca.ejercicios_practica.model.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @EntityGraph(attributePaths = "books")
    List<Category> findAll();

    Optional<Category> findByName(String name);
    boolean existsByName(String name);
}
