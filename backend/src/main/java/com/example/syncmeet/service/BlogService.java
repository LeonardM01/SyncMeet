package com.example.syncmeet.service;

import com.example.syncmeet.dto.blog.BlogDTO;
import com.example.syncmeet.model.Blog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface BlogService {

    BlogDTO blogToDTO(Blog blog);

    Blog blogToEntity(BlogDTO blogDTO);

    BlogDTO getBlogById(UUID blogId);

    List<BlogDTO> getBlogByAuthor(UUID authorId);

    List<BlogDTO> getBlogByTag(String tag);

    List<BlogDTO> getBlogBetweenCreationTime(LocalDateTime startDate, LocalDateTime endDate);

    BlogDTO createBlogPost(BlogDTO blog, UUID authorId);

    BlogDTO updateBlogPost(BlogDTO blog, UUID id);

    void deleteBlogPost(UUID blogId);
}
