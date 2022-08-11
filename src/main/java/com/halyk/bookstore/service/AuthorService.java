package com.halyk.bookstore.service;

import com.halyk.bookstore.data.representation.AuthorRepresentation;
import com.halyk.bookstore.data.request.AuthorRequest;

import java.util.List;


public interface AuthorService {

    AuthorRepresentation getAuthorById(Long id);

    List<AuthorRepresentation> getAllAuthor();

    Long saveAuthor(AuthorRequest dto);

    AuthorRepresentation getAuthorByName(String name, String last, String patron);
}
