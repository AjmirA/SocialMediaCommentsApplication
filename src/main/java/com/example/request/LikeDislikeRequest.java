package com.example.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class LikeDislikeRequest implements Serializable {
    private String commentId;
    private String userId;
}
