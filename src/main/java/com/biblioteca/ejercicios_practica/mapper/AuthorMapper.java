package com.biblioteca.ejercicios_practica.mapper;

import com.biblioteca.ejercicios_practica.dto.AuthorRequest;
import com.biblioteca.ejercicios_practica.dto.AuthorResponse;
import com.biblioteca.ejercicios_practica.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface AuthorMapper {
    Author toAuthor (AuthorRequest authorRequest);
    AuthorResponse toResponse (Author author);
}
