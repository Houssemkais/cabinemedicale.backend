package com.pfe.exception;

public class DomainException extends Exception {

    private Error error;

    public DomainException(Error error) {
        this.error = error;
    }

    public DomainException(String message, Error error) {
        super(message);
        this.error = error;
    }

    public DomainException(String message, Throwable cause, Error error) {
        super(message, cause);
        this.error = error;
    }

    public DomainException(Throwable cause, Error error) {
        super(cause);
        this.error = error;
    }

    public DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Error error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
