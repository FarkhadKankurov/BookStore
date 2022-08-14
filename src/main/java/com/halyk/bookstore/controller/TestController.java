package com.halyk.bookstore.controller;

import org.openapi.example.api.CommentsApi;
import org.openapi.example.model.BaseResponse;
import org.openapi.example.model.CommentCreateUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class TestController implements CommentsApi {
    @Override
    public ResponseEntity<BaseResponse> createComment(UUID id, @Valid CommentCreateUpdateRequest commentCreateUpdateRequest) throws Exception {
        return CommentsApi.super.createComment(id, commentCreateUpdateRequest);
    }

    @Override
    public ResponseEntity<BaseResponse> getId() throws Exception {
        return CommentsApi.super.getId();
    }
}
