package com.halyk.bookstore.exception;

public class OrderStatusNotCorrect extends RuntimeException {
    public OrderStatusNotCorrect(String message) {
        super(message);
    }
}
