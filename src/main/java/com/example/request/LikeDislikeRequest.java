package com.example.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LikeDislikeRequest extends BaseRequest {
    private String commentId;
    private String userId;
}
