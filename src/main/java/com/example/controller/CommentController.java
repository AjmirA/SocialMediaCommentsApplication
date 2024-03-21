package com.example.controller;

import com.example.constants.ApplicationConstants;
import com.example.model.Comment;
import com.example.model.Dislike;
import com.example.model.Like;
import com.example.request.CommentRequest;
import com.example.request.LikeDislikeRequest;
import com.example.request.ReplyRequest;
import com.example.response.CommentsResponse;
import com.example.response.CommentResponseBody;
import com.example.response.GenericResponse;
import com.example.response.ResponseHeader;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addComment(@RequestBody CommentRequest commentRequest) {
        Comment comment = commentService.addComment(commentRequest.getPostId(), commentRequest.getUserId(), commentRequest.getContent());
        CommentResponseBody commentResponseBody= mapToResponse(comment);
        ResponseHeader responseHeader=createResponseHeader();
        GenericResponse<CommentResponseBody> genericResponse=new GenericResponse<>();
        genericResponse.setHeader(responseHeader);
        genericResponse.setBody(commentResponseBody);

        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }



    @PostMapping("/reply")
    public ResponseEntity<GenericResponse> addReply(@RequestBody ReplyRequest replyRequest) {
        Comment reply = commentService.addReply(replyRequest.getParentCommentId(), replyRequest.getUserId(), replyRequest.getContent());
        CommentResponseBody commentResponseBody= mapToResponse(reply);
        ResponseHeader responseHeader=createResponseHeader();
        GenericResponse<CommentResponseBody> genericResponse=new GenericResponse<>();
        genericResponse.setHeader(responseHeader);
        genericResponse.setBody(commentResponseBody);

        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }

    @PostMapping("/like")
    public ResponseEntity<GenericResponse> likeComment(@RequestBody LikeDislikeRequest likeDislikeRequest) {
        commentService.likeComment(likeDislikeRequest.getCommentId(), likeDislikeRequest.getUserId());
        GenericResponse<CommentsResponse> genericResponse=new GenericResponse<>();
        genericResponse.setHeader(createResponseHeader());
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }

    @PostMapping("/dislike")
    public ResponseEntity<GenericResponse> dislikeComment(@RequestBody LikeDislikeRequest likeDislikeRequest) {
        commentService.dislikeComment(likeDislikeRequest.getCommentId(), likeDislikeRequest.getUserId());
        GenericResponse<CommentsResponse> genericResponse=new GenericResponse<>();
        genericResponse.setHeader(createResponseHeader());
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<GenericResponse> getComments(@PathVariable String postId) {
        List<Comment> comments = commentService.getComments(postId);
        List<CommentResponseBody> commentResponseBodyList=mapToResponseList(comments);
        CommentsResponse commentsResponse =new CommentsResponse();
        commentsResponse.setResponseBodyList(commentResponseBodyList);

        ResponseHeader responseHeader=createResponseHeader();
        GenericResponse<CommentsResponse> genericResponse=new GenericResponse<>();
        genericResponse.setHeader(responseHeader);
        genericResponse.setBody(commentsResponse);

        return new ResponseEntity<>(genericResponse,HttpStatus.OK);
    }


    public List<CommentResponseBody> mapToResponseList(List<Comment> comments) {
        List<CommentResponseBody> commentResponseBodyList =new ArrayList<>();
        for(Comment comment:comments){
            commentResponseBodyList.add(mapToResponse(comment));
        }
        return commentResponseBodyList;
    }

    public CommentResponseBody mapToResponse(Comment comment) {
        CommentResponseBody response = new CommentResponseBody();
        response.setId(comment.getId());
        response.setPostId(comment.getPostId());
        response.setParentComment(comment.getParentComment());
        response.setReplies(comment.getReplies());
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

    private ResponseHeader createResponseHeader() {
        ResponseHeader responseHeader=new ResponseHeader();
        responseHeader.setStatusCode(ApplicationConstants.SUCCESS_CODE);
        responseHeader.setStatusMessage(ApplicationConstants.SUCCESS_MSG);
        return responseHeader;
    }
}
