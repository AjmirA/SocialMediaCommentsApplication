package com.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class CommentsResponse extends BaseResponse{
    List<CommentResponseBody> responseBodyList;

}
