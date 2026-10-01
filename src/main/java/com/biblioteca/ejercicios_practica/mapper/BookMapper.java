package com.biblioteca.ejercicios_practica.mapper;

import com.biblioteca.ejercicios_practica.dto.BookRequest;
import com.biblioteca.ejercicios_practica.dto.BookResponse;
import com.biblioteca.ejercicios_practica.model.Author;
import com.biblioteca.ejercicios_practica.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toBook (BookRequest bookRequest);

    default BookResponse toResponse (Book book) {
        if (book == null) {
            return null;
        }
        List<Author> authors = book.getAuthors() == null ? List.of() : book.getAuthors();
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getStock(),
                book.getCategory() != null ? book.getCategory().getId() : null,
                book.getCategory() != null ? book.getCategory().getName() : null,
                authors.stream().map(Author::getId).toList(),
                authors.stream().map(Author::getName).toList()
        );
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "authors", ignore = true)
    void updateFromRequestBook (BookRequest bookRequest, @MappingTarget Book book); // Mappeo el objeto request a book
}
