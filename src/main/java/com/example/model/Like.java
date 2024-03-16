package com.example.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "likes")
@Data
public class Like {
    @Id
    private String id;

    @DBRef
    private Comment comment;

    private User user;
}
