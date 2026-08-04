package com.biblioteca.ejercicios_practica.controller;

import com.biblioteca.ejercicios_practica.dto.BookRequest;
import com.biblioteca.ejercicios_practica.dto.BookResponse;
import com.biblioteca.ejercicios_practica.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/isbn/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getBooks() {
        return bookService.getAllsBooks();
    }

    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@Valid @RequestBody BookRequest bookRequest) {
        return bookService.createBook(bookRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse updateAllBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {
        return bookService.updateAllBook(id, bookRequest);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {
        return bookService.updateBook(id, bookRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook (@PathVariable Long id) {
        bookService.deleteBook(id);
    }

}