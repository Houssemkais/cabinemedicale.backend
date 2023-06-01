package com.pfe.exception;

public class InfrastructureException extends Exception {
    private Error error;

    public InfrastructureException(Error error) {
        this.error = error;
    }

    public InfrastructureException(String message, Error error) {
        super(message);
        this.error = error;
    }

    public InfrastructureException(String message, Throwable cause, Error error) {
        super(message, cause);
        this.error = error;
    }

    public InfrastructureException(Throwable cause, Error error) {
        super(cause);
        this.error = error;
    }

    public InfrastructureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Error error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }
}
