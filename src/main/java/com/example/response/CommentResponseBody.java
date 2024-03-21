package com.example.response;

import com.example.model.Comment;
import com.example.model.Dislike;
import com.example.model.Like;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommentResponseBody extends BaseResponse {

    private String id;

    private String postId;

    private Comment parentComment;
    @JsonIgnore
    private List<Comment> replies;

    private String userId;

    private String content;
    private List<Like> likes;
    private List<Dislike> dislikes;
}
