package com.example.syncmeet.controller;

import com.example.syncmeet.dto.blog.BlogCreationRequestDTO;
import com.example.syncmeet.dto.blog.BlogDTO;
import com.example.syncmeet.dto.blog.BlogUpdateRequestDTO;
import com.example.syncmeet.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("blog/{authorId}")
    public ResponseEntity<BlogDTO> createBlogPost(
            @Valid @RequestBody BlogCreationRequestDTO blogDTO,
            @PathVariable UUID authorId,
            @RequestBody MultipartFile image
    )
    {
        BlogDTO blog = new BlogDTO();
        blog.setTitle(blogDTO.getTitle());
        blog.setBody(blogDTO.getBody());
        blog.setTag(blogDTO.getTag());

        return ResponseEntity.ok(blogService.createBlogPost(blog, authorId, image));
    }

    @GetMapping("blog/get/{blogId}")
    public ResponseEntity<BlogDTO> getBlogPostById(@PathVariable UUID blogId) {
        return ResponseEntity.ok(blogService.getBlogById(blogId));
    }

    @GetMapping("blog/author/{authorId}")
    public ResponseEntity<List<BlogDTO>> getBlogPostByAuthor(@PathVariable UUID authorId) {
        return ResponseEntity.ok(blogService.getBlogByAuthor(authorId));
    }

    @GetMapping("blog/tag")
    public ResponseEntity<List<BlogDTO>> getBlogByTag(String tag) {
        return ResponseEntity.ok(blogService.getBlogByTag(tag));
    }

    @GetMapping("blog/filterDates")
    public ResponseEntity<List<BlogDTO>> getBlogBetweenCreationDates(
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<BlogDTO> blogs;

        if (startDate != null && endDate != null) {
            blogs = blogService.getBlogBetweenCreationTime(startDate, endDate);
        } else {
            blogs = null;
        }

        return ResponseEntity.ok(blogs);
    }

    @PutMapping("blog/update/{blogId}")
    public ResponseEntity<BlogDTO> updateBlogPost(
            @PathVariable UUID blogId,
            @Valid @RequestBody BlogUpdateRequestDTO updated
    )
    {
        BlogDTO blog = blogService.getBlogById(blogId);
        blog.setTitle(updated.getNewTitle());
        blog.setBody(updated.getNewBody());
        blog.setTag(updated.getNewTag());

        return ResponseEntity.ok(blogService.updateBlogPost(blog, blogId));
    }

    @PutMapping("blog/changeImage/{blogId}")
    public ResponseEntity<BlogDTO> changeBlogPostImage(
            @PathVariable UUID blogId,
            @RequestBody MultipartFile image
    )
    {
        return ResponseEntity.ok(blogService.changeBlogPostImage(blogId, image));
    }

    @DeleteMapping("blog/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteBlogPost(@Valid @PathVariable UUID id) {

        blogService.deleteBlogPost(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Blog post deleted");
        return ResponseEntity.ok(response);
    }
}
