package com.example.repository;

import com.example.model.Dislike;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DislikeRepository extends MongoRepository<Dislike, String> {
    List<Dislike> findByCommentId(String commentId);
}

