package com.mindera.api.model;

public class Error {
    private String message;
    private Integer errorCode;

    public Error(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public Error() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
