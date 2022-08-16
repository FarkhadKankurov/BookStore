package com.halyk.bookstore.data.representation.for_order;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ForOrderRepresentationBook {
    private double cost;
    private String name;
    private int numberOfPages;
    private LocalDate releaseDate;
}
