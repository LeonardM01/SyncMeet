package com.example.syncmeet.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUpdateRequestDTO {

    private String newTitle;

    private String newBody;

    private String newTag;

    private String newImageUrl;
}
