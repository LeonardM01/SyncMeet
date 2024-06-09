package com.example.syncmeet.controller;

import com.example.syncmeet.dto.blog.BlogCreationRequestDTO;
import com.example.syncmeet.dto.blog.BlogDTO;
import com.example.syncmeet.dto.blog.BlogUpdateRequestDTO;
import com.example.syncmeet.error.ErrorResponse;
import com.example.syncmeet.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@CrossOrigin(origins = {"http://localhost:3000", "https://syncmeet.space" } , allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @Operation(summary = "Create a blog post", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully created blog post",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {BlogDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("blog/{authorId}")
    public ResponseEntity<BlogDTO> createBlogPost(
            @Valid @RequestPart("data") BlogCreationRequestDTO blogDTO,
            @PathVariable UUID authorId,
            @RequestPart("image") MultipartFile image
    )
    {
        BlogDTO blog = new BlogDTO();
        blog.setTitle(blogDTO.getTitle());
        blog.setBody(blogDTO.getBody());
        blog.setTag(blogDTO.getTag());

        return ResponseEntity.ok(blogService.createBlogPost(blog, authorId, image));
    }

    @Operation(summary = "Get blog post by id", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved blog post",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {BlogDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Blog post not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("blog/get/{blogId}")
    public ResponseEntity<BlogDTO> getBlogPostById(@PathVariable UUID blogId) {
        return ResponseEntity.ok(blogService.getBlogById(blogId));
    }

    @Operation(summary = "Get blog posts by author", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved blog posts",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {BlogDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Author doesnt exist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("blog/author/{authorId}")
    public ResponseEntity<List<BlogDTO>> getBlogPostByAuthor(@PathVariable UUID authorId) {
        return ResponseEntity.ok(blogService.getBlogByAuthor(authorId));
    }

    @Operation(summary = "Get blog posts by tag", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved blog posts",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {BlogDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("blog/tag")
    public ResponseEntity<List<BlogDTO>> getBlogByTag(String tag) {
        return ResponseEntity.ok(blogService.getBlogByTag(tag));
    }

    @Operation(summary = "Get all blog posts between dates", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved blog posts",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {BlogDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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

    @Operation(summary = "Update blog post", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated blog post",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {BlogDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Blog post not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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

    @Operation(summary = "Change blog post image", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully changed blog post image",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {BlogDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Blog post not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("blog/changeImage/{blogId}")
    public ResponseEntity<BlogDTO> changeBlogPostImage(
            @PathVariable UUID blogId,
            @RequestBody MultipartFile image
    )
    {
        return ResponseEntity.ok(blogService.changeBlogPostImage(blogId, image));
    }

    @Operation(summary = "Delete blog post", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted blog post",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {BlogDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Blog post not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("blog/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteBlogPost(@Valid @PathVariable UUID id) {

        blogService.deleteBlogPost(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Blog post deleted");
        return ResponseEntity.ok(response);
    }
}
