package com.example.response;

import com.example.model.Comment;
import com.example.model.Dislike;
import com.example.model.Like;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class CommentResponse implements Serializable {

    private String id;

    private String postId;

    private Comment parentComment;
    @JsonIgnore
    private List<CommentResponse> replies;

    private String userId;

    private String content;
    private List<Like> likes;
    private List<Dislike> dislikes;
}
