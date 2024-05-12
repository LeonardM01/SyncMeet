package com.example.syncmeet.dto.comment;

import com.example.syncmeet.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreationRequestDTO {

    private String content;

    private UUID userId;

    private UUID eventId;
}
