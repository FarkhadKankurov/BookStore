package com.halyk.bookstore.service.impl;

import com.halyk.bookstore.data.representation.AuthorRepresentation;
import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.data.entity.Author;
import com.halyk.bookstore.data.mapper.AuthorMapper;
import com.halyk.bookstore.data.repository.AuthorRepository;
import com.halyk.bookstore.service.AuthorService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    private final AuthorMapper mapper;

    @Transactional
    @Override
    public AuthorRepresentation getAuthorById(Long id) {
        Author author = repository.findByIdOrThrowException(id);
        return mapper.fromEntity(author);
    }

    @Transactional
    @Override
    public List<AuthorRepresentation> getAllAuthor() {
        List<Author> list = repository.findAll();
        return list.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long saveAuthor(AuthorRequest dto) {
        Author author = mapper.toEntity(dto);
        Author save = repository.save(author);
        return save.getId();
    }


    @Transactional
    @Override
    public AuthorRepresentation getAuthorByName(String name, String last, String patron) {
        Author author = repository.findAuthorByFirstNameAndLastNameAndPatronymicName(name, last, patron);
        return mapper.fromEntity(author);
    }
}
