package com.example.exception;

import com.example.constants.ErrorCode;

public class JWTException extends BaseException {
    private final ErrorCode errorCode;

    public JWTException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return errorCode.getStatusCode();
    }
}
