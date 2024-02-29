package com.mindera.api.exception;

public class GenderNotFoundException extends RuntimeException{
    public GenderNotFoundException(String message){
        super(message);
    }
}
