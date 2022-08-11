package com.halyk.bookstore.data.representation;

import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.data.request.BookRequest;
import lombok.Data;

import java.util.List;

@Data
public class GenreRepresentation {
    private String genreName;
    private List<BookRepresentation> books;
    private List<AuthorRepresentation> authors;
}
