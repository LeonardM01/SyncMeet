package com.example.syncmeet.service;

import com.example.syncmeet.dto.CommentDTO;
import com.example.syncmeet.model.Comment;

public interface CommentService {

    CommentDTO commentToDTO(Comment comment);

    Comment commentDTOToEntity(CommentDTO commentDTO);

    CommentDTO getCommentById(Long id);

    CommentDTO createComment(CommentDTO commentDTO);

    void deleteComment(Long id);
}
