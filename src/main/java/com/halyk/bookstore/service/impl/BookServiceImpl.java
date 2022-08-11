package com.halyk.bookstore.service.impl;

import com.halyk.bookstore.data.request.BookRequest;
import com.halyk.bookstore.data.entity.Book;
import com.halyk.bookstore.data.mapper.BookMapper;
import com.halyk.bookstore.data.repository.BookRepository;
import com.halyk.bookstore.data.representation.BookRepresentation;
import com.halyk.bookstore.service.BookService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class BookServiceImpl implements BookService {


    private final BookRepository repository;

    private final BookMapper mapper;

    @Transactional
    @Override
    public BookRepresentation getBookById(Long id) {
        Book book = repository.findByIdOrThrowException(id);
        return mapper.fromEntity(book);

    }

    @Transactional
    @Override
    public Long saveBook(BookRequest dto) {
        Book book = mapper.toEntity(dto);
        Book save = repository.save(book);
        return save.getId();
    }

    @Transactional
    @Override
    public void updateBook(BookRequest bookdto, Long id) {
        Book book = repository.findByIdOrThrowException(id);
        mapper.updateEntity(book, bookdto);
        repository.save(book);
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        return repository.deleteBookById(id);
    }

    @Transactional
    @Override
    public List<BookRepresentation> getAllBook() {
        List<Book> allBook = repository.findAll();
        return allBook.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<BookRepresentation> getBookByName(String name) {
        List<Book> allBook = repository.findBookByName(name);
        return allBook.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }
}
