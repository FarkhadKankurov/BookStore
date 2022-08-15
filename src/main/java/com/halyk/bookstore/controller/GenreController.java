package com.halyk.bookstore.controller;

import com.halyk.bookstore.data.representation.GenreRepresentation;
import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.data.request.GenreRequest;
import com.halyk.bookstore.service.GenreService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<GenreRepresentation> getBookById(@PathVariable Long id){
        GenreRepresentation genreById = service.getGenreById(id);
        return ResponseEntity.ok(genreById);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<GenreRepresentation>> getAllGenre(){
        List<GenreRepresentation> list = service.getAllGenre();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<GenreRepresentation>> getGenreByName(@PathVariable String name){
        List<GenreRepresentation> list = service.getGenreByName(name);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> saveGenre(@RequestBody GenreRequest dto){
        long id = service.saveGenre(dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> deleteGenre(@PathVariable Long id){
        Long count = service.delete(id);
        return ResponseEntity.ok(count);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody GenreRequest dto){
        service.updateGenre(dto, id);
        return ResponseEntity.ok("Success");
    }

    
}
