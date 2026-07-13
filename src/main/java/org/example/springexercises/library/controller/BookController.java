package org.example.springexercises.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springexercises.library.dtos.BookRequestDTO;
import org.example.springexercises.library.model.BookEntity;
import org.example.springexercises.library.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<BookEntity>> getBooks() {
        List<BookEntity> books = bookRepository.findAll();

        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<BookEntity> createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookEntity bookEntity = new BookEntity(bookRequestDTO.title(), bookRequestDTO.author(), bookRequestDTO.publicationYear(), false);
        BookEntity createdBook = bookRepository.save(bookEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
}
