package com.halyk.bookstore.controller;

import com.halyk.bookstore.data.representation.PublisherRepresentation;
import com.halyk.bookstore.data.request.PublisherRequest;
import com.halyk.bookstore.service.PublisherService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/publisher")
public class PublisherController {

    private final PublisherService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PublisherRepresentation> getPublisherById(@PathVariable Long id) {
        PublisherRepresentation publisherById = service.getPublisherById(id);
        return ResponseEntity.ok(publisherById);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<PublisherRepresentation>> getAllPublisher() {
        List<PublisherRepresentation> allPublisher = service.getAllPublisher();
        return ResponseEntity.ok(allPublisher);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PublisherRepresentation> getPublisherByName(@PathVariable String name) {
        PublisherRepresentation dto = service.getPublisherByName(name);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/partname/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<PublisherRepresentation>> getBookByPartOfName(@PathVariable String name) {
        List<PublisherRepresentation> list = service.getAuthorByPartName(name);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> savePublisher(@RequestBody PublisherRequest dto) {
        long id = service.savePublisher(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updatePublisher(@PathVariable Long id, @RequestBody PublisherRequest dto) {
        service.updatePublisher(id, dto);
        return ResponseEntity.ok("Success");

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> deletePublisher(@PathVariable Long id) {
        Long count = service.delete(id);
        return ResponseEntity.ok(count);
    }

}
