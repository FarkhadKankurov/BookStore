package com.halyk.bookstore.data.representation;

import com.halyk.bookstore.data.request.BookRequest;
import com.halyk.bookstore.data.request.GenreRequest;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AuthorRepresentation {
    private String lastName;
    private String firstName;
    private String patronymicName;
    private LocalDate dateOfBirth;
    private List<BookRepresentation> books;
    private List<GenreRepresentation> genres;
}
