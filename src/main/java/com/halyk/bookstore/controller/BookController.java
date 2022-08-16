package com.halyk.bookstore.controller;

import com.halyk.bookstore.data.request.BookRequest;
import com.halyk.bookstore.data.repository.user.UserRepository;
import com.halyk.bookstore.data.representation.BookRepresentation;
import com.halyk.bookstore.service.BookService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Data
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    private final UserRepository repository;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<BookRepresentation> getBookById(@PathVariable Long id) {
        BookRepresentation bookById = bookService.getBookById(id);
        return ResponseEntity.ok(bookById);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<BookRepresentation>> getAllBook() {
        List<BookRepresentation> list = bookService.getAllBook();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<BookRepresentation>> getBookByName(@PathVariable String name) {
        List<BookRepresentation> list = bookService.getBookByName(name);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/genres")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<BookRepresentation> getBookByGenres(@RequestBody List<String> genres) {
        return bookService.getBooksByGenreName(genres);
    }

    @GetMapping("/partname/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<BookRepresentation>> getBookByPartOfName(@PathVariable String name) {
        List<BookRepresentation> list = bookService.getBookByPartOfName(name);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> saveBook(@RequestBody BookRequest dto) {
        long id = bookService.saveBook(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookRequest dto) {
        bookService.updateBook(dto, id);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> deleteBook(@PathVariable Long id) {
        Long count = bookService.delete(id);
        return ResponseEntity.ok(count);
    }
}
