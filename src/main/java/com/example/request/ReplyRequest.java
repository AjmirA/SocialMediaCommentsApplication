package com.example.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ReplyRequest extends BaseRequest {
    private String parentCommentId;
    private String userId;
    private String content;

}