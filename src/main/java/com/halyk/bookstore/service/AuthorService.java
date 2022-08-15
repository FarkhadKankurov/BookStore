package com.halyk.bookstore.service;

import com.halyk.bookstore.data.representation.AuthorRepresentation;
import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.data.request.BookRequest;

import java.util.List;


public interface AuthorService {

    AuthorRepresentation getAuthorById(Long id);

    List<AuthorRepresentation> getAllAuthor();

    Long saveAuthor(AuthorRequest dto);

    List<AuthorRepresentation> getAuthorByName(AuthorRequest dto);

    Long delete(Long id);

    List<AuthorRepresentation> getAuthorsByGenreName(String genreName);

    void updateAuthor(AuthorRequest dto, Long id);
}
