package com.drinktea.exception;

public class ShoppingCartBusinessException extends RuntimeException {
    public ShoppingCartBusinessException() {}
    public ShoppingCartBusinessException(String message) {
        super(message);
    }
}
