package com.bosch.productapp.exception;

public class NoProductFoundException extends Exception {
    public NoProductFoundException() {
        super();
    }
    public NoProductFoundException(String message) {
        super(message);
    }
    public NoProductFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
