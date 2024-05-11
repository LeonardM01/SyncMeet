package com.example.syncmeet.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUpdateRequestDTO {

    private String newTitle;

    private String newBody;

    private LocalDateTime updatedAt;
}
