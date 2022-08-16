package com.halyk.bookstore.service;

import com.halyk.bookstore.data.request.BookRequest;
import com.halyk.bookstore.data.representation.BookRepresentation;

import java.util.List;


public interface BookService {

    BookRepresentation getBookById(Long id);

    Long saveBook(BookRequest book);

    void updateBook(BookRequest bookdto, Long id);

    Long delete(Long id);

    List<BookRepresentation> getAllBook();

    List<BookRepresentation> getBookByName(String name);

    List<BookRepresentation> getBookByPartOfName(String name);
}
