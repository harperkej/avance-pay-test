package com.avance.test.avancepaytest.exception;

/**
 * Created by a.kuci on 7/28/2017.
 */
public class RestApiException extends Exception {

    private String message;

    private String errorMessage;

    private ExceptionCause exceptionCause;

    public RestApiException(String message, String errorMessage, ExceptionCause exceptionCause) {
        super(message);
        this.message = message;
        this.errorMessage = errorMessage;
        this.exceptionCause = exceptionCause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionCause getExceptionCause() {
        return exceptionCause;
    }

    public void setExceptionCause(ExceptionCause exceptionCause) {
        this.exceptionCause = exceptionCause;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
