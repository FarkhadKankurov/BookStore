package com.halyk.bookstore.data.request;

import com.halyk.bookstore.data.entity.Author;
import com.halyk.bookstore.data.entity.Genre;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookRequest {
    private double cost;
    private String name;
    private int numberOfPages;
    private LocalDate releaseDate;
    private Long publisherId;
    private List<Author> authors;
    private List<Genre> genres;
}
