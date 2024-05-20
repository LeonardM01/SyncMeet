package com.example.syncmeet.dto.blog;

import com.example.syncmeet.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {

    private UUID id;

    private String title;

    private String body;

    private UserDTO author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String type;
}
