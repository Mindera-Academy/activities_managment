package com.mindera.api.exception;

public class DocumentIsRequiredException extends RuntimeException {
    public DocumentIsRequiredException(String message) {

        super(message);
    }

    public DocumentIsRequiredException(String message, Throwable cause){
        super(message, cause);
    }
}
