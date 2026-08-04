package com.biblioteca.ejercicios_practica.repository;

import com.biblioteca.ejercicios_practica.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    long countByCategoryId (Long id);
    Optional<Book> findByIsbn (String isbn);
    Boolean existsByIsbn (String isbn);
}
