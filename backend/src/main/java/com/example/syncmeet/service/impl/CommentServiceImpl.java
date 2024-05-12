package com.example.syncmeet.service.impl;

import com.example.syncmeet.dto.comment.CommentDTO;
import com.example.syncmeet.error.exception.EntityNotFoundException;
import com.example.syncmeet.model.Comment;
import com.example.syncmeet.repository.CommentRepository;
import com.example.syncmeet.service.CommentService;
import com.example.syncmeet.service.EventService;
import com.example.syncmeet.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final EventService eventService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, EventService eventService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO commentToDTO(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public Comment commentToEntity(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }

    @Override
    public CommentDTO getCommentById(UUID id) {
        return commentRepository.findById(id)
                .map(this::commentToDTO).orElseThrow(() -> new EntityNotFoundException("Couldn't find comment"));
    }

    @Override
    public CommentDTO createComment(CommentDTO comment, UUID userId, UUID eventId) {

        comment.setUser(userService.getUserById(userId));
        comment.setEvent(eventService.getEventById(eventId));
        return commentToDTO(commentRepository.save(commentToEntity(comment)));
    }

    @Override
    public CommentDTO updateComment(CommentDTO comment, UUID id) {
        if (!commentRepository.existsById(id)) {
            throw new EntityNotFoundException("Comment not found");
        }

        return commentToDTO(commentRepository.save(commentToEntity(comment)));
    }

    @Override
    public void deleteComment(UUID id) {
        if (!commentRepository.existsById(id)) {
            throw new EntityNotFoundException("Comment not found");
        }

        commentRepository.deleteById(id);
    }

}