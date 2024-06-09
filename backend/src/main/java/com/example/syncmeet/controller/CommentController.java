package com.example.syncmeet.controller;

import com.example.syncmeet.dto.blog.BlogDTO;
import com.example.syncmeet.dto.comment.CommentCreationRequestDTO;
import com.example.syncmeet.dto.comment.CommentDTO;
import com.example.syncmeet.dto.comment.CommentReplyRequestDTO;
import com.example.syncmeet.dto.comment.CommentUpdateRequestDTO;
import com.example.syncmeet.error.ErrorResponse;
import com.example.syncmeet.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create a comment", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully created comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {BlogDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User or Event not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/comment/create")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentCreationRequestDTO comment)
    {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(comment.getContent());

        return ResponseEntity.ok(commentService.createComment(commentDTO, comment.getUserId(), comment.getEventId()));
    }

    @Operation(summary = "Get a comment by id", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {CommentDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comment not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/comment/get/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable UUID id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @Operation(summary = "Get all comments of a user", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved comments",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {CommentDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/comment/get/user/{userId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(commentService.getCommentsByUserId(userId));
    }

    @Operation(summary = "Update comment", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {CommentDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comment not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/comment/update/{id}")
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentUpdateRequestDTO updatedComment , @PathVariable UUID id) {

        CommentDTO comment = commentService.getCommentById(id);
        comment.setContent(updatedComment.getNewContent());

        return ResponseEntity.ok(commentService.updateComment(comment, id));
    }

    @Operation(summary = "Reply to a comment", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully replied to comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {CommentDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Parent comment not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/comment/reply/{parentId}")
    public ResponseEntity<CommentDTO> replyToComment(@Valid @RequestBody CommentReplyRequestDTO comment, @PathVariable UUID parentId) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(comment.getContent());
        return ResponseEntity.ok(commentService.replyToComment(commentDTO, comment.getUserId(), parentId));
    }

    @Operation(summary = "Delete a comment", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {CommentDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comment not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable UUID id) {
        commentService.getCommentById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comment removed");
        commentService.deleteComment(id);
        return ResponseEntity.ok(response);
    }

}
