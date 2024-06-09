package com.example.syncmeet.controller;

import com.example.syncmeet.dto.comment.CommentCreationRequestDTO;
import com.example.syncmeet.dto.comment.CommentDTO;
import com.example.syncmeet.dto.comment.CommentReplyRequestDTO;
import com.example.syncmeet.dto.comment.CommentUpdateRequestDTO;
import com.example.syncmeet.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "https://syncmeet.space" } , allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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

    @GetMapping("/comment/get/user/{userId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(commentService.getCommentsByUserId(userId));
    }

    @PutMapping("/comment/update/{id}")
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentUpdateRequestDTO updatedComment , @PathVariable UUID id) {

        CommentDTO comment = commentService.getCommentById(id);
        comment.setContent(updatedComment.getNewContent());

        return ResponseEntity.ok(commentService.updateComment(comment, id));
    }

    @PostMapping("/comment/reply/{parentId}")
    public ResponseEntity<CommentDTO> replyToComment(@Valid @RequestBody CommentReplyRequestDTO comment, @PathVariable UUID parentId) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(comment.getContent());
        return ResponseEntity.ok(commentService.replyToComment(commentDTO, comment.getUserId(), parentId));
    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable UUID id) {
        commentService.getCommentById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comment removed");
        commentService.deleteComment(id);
        return ResponseEntity.ok(response);
    }

}
