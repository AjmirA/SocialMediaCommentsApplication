package com.example.response;

import lombok.Data;

@Data
public class ResponseHeader extends BaseResponse{
    private String statusCode;
    private String statusMessage;
}
