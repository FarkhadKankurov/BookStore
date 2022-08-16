package com.halyk.bookstore.data.representation;

import com.halyk.bookstore.data.representation.for_order.ForOrderRepresentationBook;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class OrderRepresentation {
    private String status;
    private List<ForOrderRepresentationBook> books;
    private OffsetDateTime dateTime;
}
