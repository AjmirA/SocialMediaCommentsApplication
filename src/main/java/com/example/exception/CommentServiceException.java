package com.example.exception;

import com.example.constants.ErrorCode;

public class CommentServiceException extends BaseException {

    ErrorCode errorCode;
    public CommentServiceException(String message) {
        super(message);
    }
    public CommentServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }
}

