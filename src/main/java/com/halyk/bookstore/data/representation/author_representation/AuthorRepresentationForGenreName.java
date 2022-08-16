package com.halyk.bookstore.data.representation.author_representation;


import com.halyk.bookstore.data.representation.GenreRepresentation;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AuthorRepresentationForGenreName {
    private String lastName;
    private String firstName;
    private String patronymicName;
    private LocalDate dateOfBirth;
    private List<GenreRepresentation> genres;
}
