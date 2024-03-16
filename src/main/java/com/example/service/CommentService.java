package com.example.service;

import com.example.model.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(String postId, String userId, String content);
    Comment addReply(String parentCommentId, String userId, String content);
    void likeComment(String commentId, String userId);
    void dislikeComment(String commentId, String userId);
    List<Comment> getComments(String postId);
}
