package com.avance.test.avancepaytest.dto;

/**
 * Created by a.kuci on 7/28/2017.
 */
public class ExceptionResponseDto {

    private String message;

    private String errorMessage;

    private String timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
