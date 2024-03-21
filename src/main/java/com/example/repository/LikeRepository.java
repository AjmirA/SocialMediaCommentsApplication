package com.example.repository;

import com.example.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LikeRepository extends MongoRepository<Like, String> {
    List<Like> findByCommentId(String commentId);

    Like save(Like like);
}

