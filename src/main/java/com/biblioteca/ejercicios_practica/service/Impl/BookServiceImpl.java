package com.biblioteca.ejercicios_practica.service.Impl;

import com.biblioteca.ejercicios_practica.dto.BookRequest;
import com.biblioteca.ejercicios_practica.dto.BookResponse;
import com.biblioteca.ejercicios_practica.exception.ResourceAlreadyExistsException;
import com.biblioteca.ejercicios_practica.exception.ResourceNotFoundException;
import com.biblioteca.ejercicios_practica.mapper.BookMapper;
import com.biblioteca.ejercicios_practica.model.Author;
import com.biblioteca.ejercicios_practica.model.Book;
import com.biblioteca.ejercicios_practica.model.Category;
import com.biblioteca.ejercicios_practica.repository.AuthorRepository;
import com.biblioteca.ejercicios_practica.repository.BookRepository;
import com.biblioteca.ejercicios_practica.service.BookService;
import com.biblioteca.ejercicios_practica.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryService categoryService;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public BookResponse createBook(BookRequest bookRequest) {
        if (bookRepository.existsByIsbn(bookRequest.isbn())) {
            throw new  ResourceAlreadyExistsException("Book", "isbn", bookRequest.isbn());
        }
        Book book = bookMapper.toBook(bookRequest);
        Category category = categoryService.getCategoryEntityById(bookRequest.categoryId());
        book.setCategory(category);
        List<Author> authors = authorRepository.findAllById(bookRequest.authorIds());
        book.setAuthors(authors);
        Book createdBook = bookRepository.save(book);
        log.info("Book {} created and saved in the library", createdBook.getTitle());

        return bookMapper.toResponse(createdBook);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAllsBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", id)
        );
        return bookMapper.toResponse(book);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new ResourceNotFoundException("Book", "isbn", isbn)
        );
        return bookMapper.toResponse(book);
    }

    @Override
    @Transactional
    public BookResponse updateAllBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", id)
        );
        bookMapper.updateFromRequestBook(bookRequest, book);
        Book savedBook = bookRepository.save(book);
        log.info("Book updated! {} - ID: {}", book.getTitle(), book.getId());

        return bookMapper.toResponse(savedBook);
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", id)
        );
        if (bookRequest.stock() != null) book.setStock(bookRequest.stock());
        if (bookRequest.title() != null) book.setTitle(bookRequest.title());
        Book savedBook = bookRepository.save(book);
        log.info("Field updated {} - STOCK {}", savedBook.getTitle(), savedBook.getStock());
        return bookMapper.toResponse(savedBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book", "id", id);
        }
        bookRepository.deleteById(id);

    }
}
