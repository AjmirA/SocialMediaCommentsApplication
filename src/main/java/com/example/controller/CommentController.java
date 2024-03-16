package com.example.controller;

import com.example.model.Comment;
import com.example.model.Dislike;
import com.example.model.Like;
import com.example.request.CommentRequest;
import com.example.request.LikeDislikeRequest;
import com.example.request.ReplyRequest;
import com.example.response.CommentResponse;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody CommentRequest commentRequest) {
        Comment comment = commentService.addComment(commentRequest.getPostId(), commentRequest.getUserId(), commentRequest.getContent());
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PostMapping("/reply")
    public ResponseEntity<Comment> addReply(@RequestBody ReplyRequest replyRequest) {
        Comment reply = commentService.addReply(replyRequest.getParentCommentId(), replyRequest.getUserId(), replyRequest.getContent());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{commentId}/like")
    public ResponseEntity<Void> likeComment(@PathVariable String commentId, @RequestBody LikeDislikeRequest likeDislikeRequest) {
        commentService.likeComment(commentId, likeDislikeRequest.getUserId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{commentId}/dislike")
    public ResponseEntity<Void> dislikeComment(@PathVariable String commentId, @RequestBody LikeDislikeRequest likeDislikeRequest) {
        commentService.dislikeComment(commentId, likeDislikeRequest.getUserId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable String postId) {
        List<Comment> comments = commentService.getComments(postId);
        return new ResponseEntity<>(mapToResponseList(comments),HttpStatus.OK);
    }


    public List<CommentResponse> mapToResponseList(List<Comment> comments) {
        List<CommentResponse> commentResponseList=new ArrayList<>();
        for(Comment comment:comments){
            commentResponseList.add(mapToResponse(comment));
        }
        return commentResponseList;
    }

    public CommentResponse mapToResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setPostId(comment.getPostId());
        response.setParentComment(comment.getParentComment());
        response.setReplies(mapToResponseList(comment.getReplies()));
        response.setUserId(comment.getUserId());
        response.setContent(comment.getContent());
        List<Like> likeList=new ArrayList<>();
        for(Like like:comment.getLikes()){
            likeList.add(like);
        }
        response.setLikes(likeList);

        List<Dislike> disLikeList=new ArrayList<>();
        for(Dislike dislike:comment.getDislikes()){
            disLikeList.add(dislike);
        }
        response.setDislikes(disLikeList);

        return response;
    }
}
