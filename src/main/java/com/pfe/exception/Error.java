package com.pfe.exception;

public enum Error {
    SERVER_ERROR(500, "Server error"),
    NOT_FOUND_EXCEPTION(404, "Not found exception"),
    BAD_REQUEST(400, "Bad request"),
    USER_EMAIL_ALREADY_EXISTS(400, "Bad request"),

    USER_EMAIL_NOT_FOUND(400, "Bad request"),

    BAD_CREDENTIEL(403, "Bad credentiel"),

    UNOTHORIZED(401, "UNOTHORIZED"), INVALID_RESET_TOKEN(400,"Bad request" ), EXPIRED_RESET_TOKEN(400, "expired");



    int code;
    String error;

    Error(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }
}
