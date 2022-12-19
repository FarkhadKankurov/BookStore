package com.halyk.bookstore.service.impl;

import com.halyk.bookstore.data.request.BookRequest;
import com.halyk.bookstore.data.entity.Book;
import com.halyk.bookstore.data.mapper.BookMapper;
import com.halyk.bookstore.data.repository.BookRepository;
import com.halyk.bookstore.data.representation.BookRepresentation;
import com.halyk.bookstore.service.BookService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper mapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public BookRepresentation getBookById(Long id) {
        Book book = bookRepository.findByIdOrThrowException(id);
        return mapper.fromEntity(book);

    }

    @Override
    public List<BookRepresentation> getAllBook() {
        List<Book> allBook = bookRepository.findAll();
        return allBook.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<BookRepresentation> getBookByName(String name) {
        List<Book> allBook = bookRepository.findBookByName(name);
        return allBook.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<BookRepresentation> getBookByPartOfName(String name) {
        List<Book> books = bookRepository.findBookByNameStartingWith(name);
        return books.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<BookRepresentation> getBooksByGenreName(List<String> genres) {
        List<BookRepresentation> bookRepresentations = new ArrayList<>();
        for (String  s : genres) {
            bookRepresentations.add(mapper.fromEntity(bookRepository.findAllByGenre(s)));
        }
        return bookRepresentations;
//        bookRepository.findAllByGenre(genres).stream().map(mapper::fromEntity).toList();
    }

    @Transactional
    @Override
    public Long saveBook(BookRequest dto) {
        Book book = mapper.toEntity(dto);
        Book save = bookRepository.save(book);
        return save.getId();
    }

    @Transactional
    @Override
    public void updateBook(BookRequest bookdto, Long id) {
        Book book = bookRepository.findByIdOrThrowException(id);
        mapper.updateEntity(book, bookdto);
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        return bookRepository.deleteBookById(id);
    }

}
