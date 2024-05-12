package com.example.syncmeet.controller;

import com.example.syncmeet.dto.blog.BlogDTO;
import com.example.syncmeet.dto.comment.CommentCreationRequestDTO;
import com.example.syncmeet.dto.comment.CommentDTO;
import com.example.syncmeet.dto.comment.CommentUpdateRequestDTO;
import com.example.syncmeet.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/create")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentCreationRequestDTO comment)
    {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(comment.getContent());

        return ResponseEntity.ok(commentService.createComment(commentDTO, comment.getUserId(), comment.getEventId()));
    }

    @GetMapping("/comment/get/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable UUID id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PutMapping("/comment/update/{id}")
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentUpdateRequestDTO updatedComment , @PathVariable UUID id) {

        CommentDTO comment = commentService.getCommentById(id);
        comment.setContent(updatedComment.getNewContent());

        return ResponseEntity.ok(commentService.updateComment(comment, id));
    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable UUID id) {
        CommentDTO comment = commentService.getCommentById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comment removed");
        commentService.deleteComment(id);
        return ResponseEntity.ok(response);
    }

}
