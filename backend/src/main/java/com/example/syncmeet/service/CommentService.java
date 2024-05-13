package com.example.syncmeet.service;

import com.example.syncmeet.dto.comment.CommentDTO;
import com.example.syncmeet.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    CommentDTO toDTO(Comment comment);

    Comment toEntity(CommentDTO commentDTO);

    CommentDTO getCommentById(UUID id);

    List<CommentDTO> getCommentsByUserId(UUID userId);

    CommentDTO createComment(CommentDTO comment, UUID userId, UUID eventId);

    CommentDTO updateComment(CommentDTO comment, UUID id);

    CommentDTO replyToComment(CommentDTO comment, UUID userId, UUID parentId);

    void deleteComment(UUID id);
}
