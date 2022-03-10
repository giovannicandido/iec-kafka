package com.example.ecommerce.ecommerce.exception;

public class DomainBusinessException extends RuntimeException {
    public DomainBusinessException(String message) {
        super(message);
    }
}
