package com.example.constants;

public enum ErrorCode {
    MISSING_CLIENT_CREDENTIALS("Missing client credentials", 401),
    INVALID_CLIENT_CREDENTIALS("Invalid client credentials", 401),
    INVALID_TOKEN("Invalid JWT token", 401),
    EXPIRED_TOKEN("Expired JWT token", 401),
    USER_NOT_FOUNT("User details not exists",404),
    COMMAND_NOT_FOUNT("Command details not exists",404),
    PARENT_COMMAND_NOT_FOUNT("Parent command details not exists",404);




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
