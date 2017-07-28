package com.avance.test.avancepaytest.exception;

/**
 * Created by a.kuci on 7/28/2017.
 */
public class ServiceException extends Exception {

    private String errorMessage;

    private String message;

    private ExceptionCause exceptionCause;

    public ServiceException(String message, String errorMessage, ExceptionCause exceptionCause) {
        super(message);
        this.message = message;
        this.errorMessage = errorMessage;
        this.exceptionCause = exceptionCause;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
}
