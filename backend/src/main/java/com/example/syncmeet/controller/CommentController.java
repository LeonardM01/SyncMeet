package com.example.syncmeet.controller;

import com.example.syncmeet.dto.CommentDTO;
import com.example.syncmeet.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/comment")
    public ResponseEntity<Map<String, Object>> createComment(@Valid @RequestBody CommentDTO comment) {
        commentService.createComment(comment);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comment Created successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/comment/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable Long id) {
        CommentDTO comment = commentService.getCommentById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comment removed");
        commentService.deleteComment(id);
        return ResponseEntity.ok(response);
    }

}
