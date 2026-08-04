package com.biblioteca.ejercicios_practica.service;

import com.biblioteca.ejercicios_practica.dto.AuthorRequest;
import com.biblioteca.ejercicios_practica.dto.AuthorResponse;
import com.biblioteca.ejercicios_practica.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthorService{

    AuthorResponse getAuthorByName (String name);
    AuthorResponse getAuthorById (Long id);
    List<AuthorResponse> getAuthors ();

    AuthorResponse createAuthor (AuthorRequest authorRequest);
    AuthorResponse updateAuthor (Long id, AuthorRequest authorRequest);
    void deleteAuthor (Long id);
}
