package com.example.syncmeet.dto.blog;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreationRequestDTO {

    @Size(min = 3, message = "Blog title is too short")
    @Size(max = 255, message = "Blog title is too long")
    private String title;

    private String body;

    @NotNull(message = "Creation date of Blog post is required")
    private LocalDateTime createdAt;

    private String type;
}
