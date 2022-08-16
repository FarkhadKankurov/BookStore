package com.halyk.bookstore.controller;

import com.halyk.bookstore.data.representation.AuthorRepresentation;
import com.halyk.bookstore.data.representation.author_representation.AuthorRepresentationForGenreName;
import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.service.AuthorService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Data
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AuthorRepresentation> getAuthorById(@PathVariable Long id) {
        AuthorRepresentation dto = authorService.getAuthorById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AuthorRepresentation>> getAllAuthor() {
        List<AuthorRepresentation> list = authorService.getAllAuthor();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/fio")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AuthorRepresentation>> getAuthorByName(@RequestBody AuthorRequest dto) {
        List<AuthorRepresentation> authorByName = authorService.getAuthorByName(dto);
        return ResponseEntity.ok(authorByName);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/genres")
    public  List<AuthorRepresentationForGenreName> findAuthorsByGenreList(@RequestBody List<String> genres){
        return authorService.findAuthorsByGenreList(genres);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest dto) {
        authorService.updateAuthor(dto, id);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> saveAuthor(@RequestBody AuthorRequest dto) {
        Long id = authorService.saveAuthor(dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> deleteAuthor(@PathVariable Long id) {
        Long count = authorService.delete(id);
        return ResponseEntity.ok(count);
    }
}
