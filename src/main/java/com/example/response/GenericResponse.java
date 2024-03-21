package com.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {

    private ResponseHeader header;
    private T body;

    public GenericResponse() {
    }

    public GenericResponse(T body, ResponseHeader header) {
        this.body = body;
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Object getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }
}
