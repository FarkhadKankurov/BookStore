package com.halyk.bookstore.data.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequest {
    private double cost;
    private String name;
    private int numberOfPages;
    private LocalDate releaseDate;
    private Long publisherId;
}
