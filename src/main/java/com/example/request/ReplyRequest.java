package com.example.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class ReplyRequest implements Serializable {
    private String parentCommentId;
    private String userId;
    private String content;

}