package com.biblioteca.ejercicios_practica.service.Impl;

import com.biblioteca.ejercicios_practica.dto.AuthorRequest;
import com.biblioteca.ejercicios_practica.dto.AuthorResponse;
import com.biblioteca.ejercicios_practica.exception.ResourceAlreadyExistsException;
import com.biblioteca.ejercicios_practica.exception.ResourceNotFoundException;
import com.biblioteca.ejercicios_practica.mapper.AuthorMapper;
import com.biblioteca.ejercicios_practica.model.Author;
import com.biblioteca.ejercicios_practica.repository.AuthorRepository;
import com.biblioteca.ejercicios_practica.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public AuthorResponse getAuthorByName(String name) {
        Author author = authorRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Author", "name", name)
        );
        return authorMapper.toResponse(author);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorResponse getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author", "id", id)
        );
        return authorMapper.toResponse(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorResponse> getAuthors() {
        return authorRepository.findAll()
                .stream().map(authorMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AuthorResponse createAuthor(AuthorRequest authorRequest) {
        if (authorRepository.existsByName(authorRequest.name())) {
            throw new ResourceAlreadyExistsException("Author", "name", authorRequest.name()
            );
        }
        Author author = authorMapper.toAuthor(authorRequest);
        Author createdAuthor = authorRepository.save(author);
        log.info("Author create: {}, {}", createdAuthor.getId(), createdAuthor.getName());
        return authorMapper.toResponse(createdAuthor);
    }

    @Override
    @Transactional
    public AuthorResponse updateAuthor(Long id, AuthorRequest authorRequest) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author", "id", id)
        );
        author.setName(authorRequest.name());
        Author savedAuthor = authorRepository.save(author);

        return authorMapper.toResponse(savedAuthor);

    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        if (! authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author", "id", id);
        }
        authorRepository.deleteById(id);
    }

}
