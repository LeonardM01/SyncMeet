package com.example.syncmeet.service;

import com.example.syncmeet.dto.comment.CommentDTO;
import com.example.syncmeet.model.Comment;

import java.util.UUID;

public interface CommentService {

    CommentDTO commentToDTO(Comment comment);

    Comment commentToEntity(CommentDTO commentDTO);

    CommentDTO getCommentById(UUID id);

    CommentDTO createComment(CommentDTO comment, UUID userId, UUID eventId);

    CommentDTO updateComment(CommentDTO comment, UUID id);

    void deleteComment(UUID id);
}
