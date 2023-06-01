package com.pfe.exception;

public enum Error {
    NOT_FOUND_EXCEPTION(404, "Not found exception"),
    BAD_REQUEST(400, "Bad request"),
    USER_EMAIL_ALREADY_EXISTS(400, "Bad request");

    int code;
    String error;

    Error(int code, String error) {
        this.code = code;
        this.error = error;
    }
}
