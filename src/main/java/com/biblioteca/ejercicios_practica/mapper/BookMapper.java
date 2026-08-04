package com.biblioteca.ejercicios_practica.mapper;

import com.biblioteca.ejercicios_practica.dto.BookRequest;
import com.biblioteca.ejercicios_practica.dto.BookResponse;
import com.biblioteca.ejercicios_practica.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toBook (BookRequest bookRequest);
    BookResponse toResponse (Book book);

    @Mapping(target = "id", ignore = true)
    void updateFromRequestBook (BookRequest bookRequest, @MappingTarget Book book);
}
