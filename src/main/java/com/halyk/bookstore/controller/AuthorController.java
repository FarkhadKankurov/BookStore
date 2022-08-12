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
    public ResponseEntity<AuthorRepresentation> getAuthorById(@PathVariable Long id){
        AuthorRepresentation dto = service.getAuthorById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AuthorRepresentation>> getAllAuthor(){
        List<AuthorRepresentation> list = service.getAllAuthor();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> saveAuthor(@RequestBody AuthorRequest dto){
        Long id = service.saveAuthor(dto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/fio/{surname}/{name}/{patronimyc}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")  //todo сделать его через боди
    public ResponseEntity<AuthorRepresentation> getAuthorByName(@PathVariable String surname,
                                                         @PathVariable String name,
                                                         @PathVariable String patronimyc){
        AuthorRepresentation authorByName = service.getAuthorByName(surname, name, patronimyc);
        return ResponseEntity.ok(authorByName);
    }





}
