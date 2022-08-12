package com.halyk.bookstore.exception;

public class BookReservedAnotherOrder extends RuntimeException{

    public BookReservedAnotherOrder (String message) {
        super(message);
    }
}
