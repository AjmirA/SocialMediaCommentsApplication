package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "comments")
@Data
@NoArgsConstructor
public class Comment {
    @Id
    private String id;

    private String postId;

    @DBRef
    private Comment parentComment;

    private List<Comment> replies = new ArrayList<>();

    private String userId;

    private String content;

    @DBRef
    private List<Like> likes = new ArrayList<>();

    @DBRef
    private List<Dislike> dislikes = new ArrayList<>();

    public Comment(String s, String postId, Object o, Object o1, String user1, String s1, Object o2, Object o3) {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }

}