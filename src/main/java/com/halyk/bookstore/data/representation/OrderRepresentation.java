package com.halyk.bookstore.data.representation;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class OrderRepresentation {
    private String status;
    private List<BookRepresentation> books;
    private OffsetDateTime dateTime;
}
