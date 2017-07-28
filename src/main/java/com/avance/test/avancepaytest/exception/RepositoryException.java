package com.avance.test.avancepaytest.exception;

/**
 * Created by a.kuci on 7/28/2017.
 */
public class RepositoryException extends Exception {

    private String errorMessage;

    public RepositoryException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
