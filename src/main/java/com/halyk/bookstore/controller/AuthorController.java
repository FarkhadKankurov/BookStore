package com.halyk.bookstore.controller;

import com.halyk.bookstore.data.representation.AuthorRepresentation;
import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.service.AuthorService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Data
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService service;


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AuthorRepresentation> getAuthorById(@PathVariable Long id) {
        AuthorRepresentation dto = service.getAuthorById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AuthorRepresentation>> getAllAuthor() {
        List<AuthorRepresentation> list = service.getAllAuthor();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/fio")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AuthorRepresentation>> getAuthorByName(@RequestBody AuthorRequest dto) {
        List<AuthorRepresentation> authorByName = service.getAuthorByName(dto);
        return ResponseEntity.ok(authorByName);
    }

    @GetMapping("/genre/{genrename}")
    private List<AuthorRepresentation> getAuthorByGenreName(@PathVariable("genreName") String genreName) {
        return service.getAuthorsByGenreName(genreName);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody AuthorRequest dto) {
        service.updateAuthor(dto, id);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> saveAuthor(@RequestBody AuthorRequest dto) {
        Long id = service.saveAuthor(dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> deleteAuthor(@PathVariable Long id) {
        Long count = service.delete(id);
        return ResponseEntity.ok(count);
    }


}
