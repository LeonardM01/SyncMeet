package com.example.syncmeet.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentReplyRequestDTO {

    private String content;

    private UUID userId;
}
