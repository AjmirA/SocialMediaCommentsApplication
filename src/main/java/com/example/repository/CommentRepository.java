package com.example.repository;

import com.example.exception.BaseException;
import com.example.exception.DatabaseException;
import com.example.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByPostIdAndParentCommentIsNull(String postId) throws BaseException;
    List<Comment> findByParentCommentId(String parentCommentId) throws BaseException;
    Comment save(Comment comment) throws BaseException;

    Optional<Comment> findById(String id);
}
