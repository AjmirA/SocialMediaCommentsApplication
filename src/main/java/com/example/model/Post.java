package com.example.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "posts")
@Data
public class Post {
    @Id
    private String id;
}
