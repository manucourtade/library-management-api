package com.biblioteca.ejercicios_practica.controller;

import com.biblioteca.ejercicios_practica.dto.AuthorRequest;
import com.biblioteca.ejercicios_practica.dto.AuthorResponse;
import com.biblioteca.ejercicios_practica.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorResponse getAuthorById (@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorResponse getAuthorByName (@PathVariable String name) {
        return authorService.getAuthorByName(name);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorResponse> getAuthors () {
        return authorService.getAuthors();
    }

    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse createAuthor (@Valid @RequestBody AuthorRequest authorRequest) {
        return  authorService.createAuthor(authorRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('LIBRARIAN')")
    public AuthorResponse updateAuthor (@PathVariable Long id, @Valid @RequestBody AuthorRequest authorRequest) {
        return authorService.updateAuthor(id, authorRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void  deleteAuthor (@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
