package com.halyk.bookstore.data.representation;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorRepresentation {
    private String lastName;
    private String firstName;
    private String patronymicName;
    private LocalDate dateOfBirth;
}
