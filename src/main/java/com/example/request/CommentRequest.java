package com.example.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class CommentRequest implements Serializable {
    private String postId;
    private String userId;
    private String content;
}