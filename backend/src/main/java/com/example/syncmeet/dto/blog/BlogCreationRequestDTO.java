package com.example.syncmeet.dto.blog;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreationRequestDTO {

    @Size(min = 3, message = "Blog title is too short")
    @Size(max = 255, message = "Blog title is too long")
    private String title;

    private String body;

    private String tag;

    private String imageUrl;
}
