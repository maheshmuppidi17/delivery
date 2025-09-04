package com.example.delivery.exception;


public class NoDeliveriesFoundException extends RuntimeException {
    public NoDeliveriesFoundException(String message) {
        super(message);
    }
}