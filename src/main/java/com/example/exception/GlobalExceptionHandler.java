package com.example.exception;

import com.example.constants.ApplicationConstants;
import com.example.constants.ErrorCode;
import com.example.exception.CommentServiceException;
import com.example.response.CommentsResponse;
import com.example.response.GenericResponse;
import com.example.response.ResponseHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommentServiceException.class)
    public ResponseEntity<GenericResponse> handleCommentServiceException(CommentServiceException ex) {
        GenericResponse<CommentsResponse> genericResponse=new GenericResponse<>();
        genericResponse.setHeader(createResponseHeader(ex.errorCode));
        return new ResponseEntity<>(genericResponse,HttpStatus.BAD_REQUEST);
    }

    private ResponseHeader createResponseHeader(ErrorCode errorCode) {
        ResponseHeader responseHeader=new ResponseHeader();
        responseHeader.setStatusCode(ApplicationConstants.FAILURE_CODE);
        responseHeader.setStatusMessage(errorCode.getMessage());
        return responseHeader;
    }
}

