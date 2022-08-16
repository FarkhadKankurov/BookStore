package com.halyk.bookstore.exception;

public class BookReservedAnotherOrderOrAbsent extends RuntimeException {

    public BookReservedAnotherOrderOrAbsent(String message) {
        super(message);
    }
}
