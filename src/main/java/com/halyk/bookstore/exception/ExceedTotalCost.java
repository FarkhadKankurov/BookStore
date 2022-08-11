package com.halyk.bookstore.exception;

public class ExceedTotalCost extends RuntimeException{

    public ExceedTotalCost(String message) {
        super(message);
    }
}
