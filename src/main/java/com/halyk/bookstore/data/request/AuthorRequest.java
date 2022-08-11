package com.halyk.bookstore.data.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorRequest {
    private String lastName;
    private String firstName;
    private String patronymicName;
    private LocalDate dateOfBirth;
}
