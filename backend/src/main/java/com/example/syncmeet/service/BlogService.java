package com.example.syncmeet.service;

import com.example.syncmeet.dto.blog.BlogDTO;
import com.example.syncmeet.model.Blog;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface BlogService {

    /**
     * Convert {@link Blog} to {@link BlogDTO}
     *
     * @param blog the entity to convert to DTO
     * @return {@link BlogDTO} created from blog
     */
    BlogDTO blogToDTO(Blog blog);

    /**
     * Convert {@link BlogDTO} to {@link Blog}
     *
     * @param blogDTO the DTO to convert to entity
     * @return {@link Blog} created from blogDTO
     */
    Blog blogToEntity(BlogDTO blogDTO);

    /**
     * Get blog by ID
     *
     * @param blogId ID of the blog to get
     * @return the {@link BlogDTO} with specified blogId
     */
    BlogDTO getBlogById(UUID blogId);

    List<BlogDTO> getBlogByAuthor(UUID authorId);

    List<BlogDTO> getBlogByTag(String tag);

    List<BlogDTO> getBlogBetweenCreationTime(LocalDateTime startDate, LocalDateTime endDate);

    BlogDTO createBlogPost(BlogDTO blog, UUID authorId, MultipartFile image);

    BlogDTO updateBlogPost(BlogDTO blog, UUID id);

    BlogDTO changeBlogPostImage(UUID blogId, MultipartFile image);

    void deleteBlogPost(UUID blogId);
}
