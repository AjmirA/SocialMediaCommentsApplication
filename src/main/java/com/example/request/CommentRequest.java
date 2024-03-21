package com.example.request;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class CommentRequest extends BaseRequest {
    private String postId;
    private String userId;
    private String content;
}