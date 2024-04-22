package com.example.syncmeet.service.impl;

import com.example.syncmeet.dto.CommentDTO;
import com.example.syncmeet.error.exception.EntityNotFoundException;
import com.example.syncmeet.model.Comment;
import com.example.syncmeet.repository.CommentRepository;
import com.example.syncmeet.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO commentToDTO(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public Comment commentDTOToEntity(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }

    @Override
    public CommentDTO getCommentById(Long id) {
        return commentRepository.findById(id)
                .map(this::commentToDTO).orElseThrow(() -> new EntityNotFoundException("Couldn't find comment"));
    }

    @Override
    public CommentDTO createComment(CommentDTO comment) {
        return commentToDTO(commentRepository.save(commentDTOToEntity(comment)));
    }

    @Override
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new EntityNotFoundException("Comment not found");
        }

        commentRepository.deleteById(id);
    }

}
