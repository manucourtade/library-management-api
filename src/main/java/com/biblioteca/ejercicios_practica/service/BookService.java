package com.biblioteca.ejercicios_practica.service;

import com.biblioteca.ejercicios_practica.dto.BookRequest;
import com.biblioteca.ejercicios_practica.dto.BookResponse;


import java.util.List;

public interface BookService {
    BookResponse createBook (BookRequest bookRequest);
    List<BookResponse> getAllsBooks ();
    BookResponse getBookById ( Long id);
    BookResponse getBookByIsbn ( String isbn);
    BookResponse updateBook ( Long id,  BookRequest bookRequest);
    BookResponse updateAllBook( Long id,  BookRequest bookRequest );
    void deleteBook (Long id);
}
