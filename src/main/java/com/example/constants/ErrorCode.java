package com.example.constants;

public enum ErrorCode {
    MISSING_CLIENT_CREDENTIALS("Missing client credentials", 401),
    INVALID_CLIENT_CREDENTIALS("Invalid client credentials", 401),
    INVALID_TOKEN("invalid JWT token", 401),
    EXPIRED_TOKEN("Expired JWT token", 401);


    private final String message;
    private final int statusCode;

    ErrorCode(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
