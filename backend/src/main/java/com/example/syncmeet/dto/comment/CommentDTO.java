package com.example.syncmeet.dto.comment;

import com.example.syncmeet.dto.event.EventDTO;
import com.example.syncmeet.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private UUID id;

    @NotNull(message = "Comment is empty")
    @Size(max = 255, message = "Comment too long")
    private String content;

    private UserDTO user;

    private EventDTO event;

    @JsonIgnore
    private CommentDTO replyingComment;

    private List<CommentDTO> replies;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
