package com.example.syncmeet.service.impl;

import com.example.syncmeet.dto.blog.BlogDTO;
import com.example.syncmeet.error.exception.EntityNotFoundException;
import com.example.syncmeet.error.exception.InvalidDateOrderException;
import com.example.syncmeet.model.Blog;
import com.example.syncmeet.repository.BlogRepository;
import com.example.syncmeet.service.BlogService;
import com.example.syncmeet.service.S3Service;
import com.example.syncmeet.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserService userService;
    private final S3Service s3Service;
    private final ModelMapper modelMapper;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, UserService userService, S3Service s3Service, ModelMapper modelMapper) {
        this.blogRepository = blogRepository;
        this.userService = userService;
        this.s3Service = s3Service;
        this.modelMapper = modelMapper;
    }

    @Override
    public BlogDTO blogToDTO(Blog blog) {
        return modelMapper.map(blog, BlogDTO.class);
    }

    @Override
    public Blog blogToEntity(BlogDTO blogDTO) {
        return modelMapper.map(blogDTO, Blog.class);
    }

    @Override
    public BlogDTO getBlogById(UUID blogId) {
        return blogRepository.findById(blogId).map(this::blogToDTO).orElseThrow(() ->
                new EntityNotFoundException("Blog post not found"));
    }

    @Override
    public List<BlogDTO> getBlogByAuthor(UUID authorId) {
        return blogRepository.findByAuthorId(authorId)
                .stream().map(this::blogToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BlogDTO> getBlogByTag(String tag) {
        return blogRepository.findByTag(tag)
                .stream().map(this::blogToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BlogDTO> getBlogBetweenCreationTime(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) throw new InvalidDateOrderException("Start date cannot be after end date");

        return blogRepository.findBlogByCreatedAtBetween(startDate, endDate)
                .stream().map(this::blogToDTO).collect(Collectors.toList());
    }

    @Override
    public BlogDTO createBlogPost(BlogDTO blog, UUID authorId, MultipartFile image) {
        if (image.isEmpty()) {
            blog.setImageUrl(null);
        }
        else if (!Objects.requireNonNull(image.getContentType()).startsWith("image/")) {
            throw new IllegalArgumentException("File must be an image");
        }
        else {
            String imageUrl = s3Service.uploadFile(image);
            blog.setImageUrl(imageUrl);
        }

        blog.setAuthor(userService.getUserById(authorId));
        return blogToDTO(blogRepository.save(blogToEntity(blog)));
    }

    @Override
    public BlogDTO updateBlogPost(BlogDTO blog, UUID id) {
        if (!blogRepository.existsById(id)) {
            throw new EntityNotFoundException("Blog post not found");
        }

        return blogToDTO(blogRepository.save(blogToEntity(blog)));
    }

    @Override
    public BlogDTO changeBlogPostImage(UUID blogId, MultipartFile image) {
        BlogDTO blog = getBlogById(blogId);

        if (image.isEmpty()) {
            blog.setImageUrl(null);

            return updateBlogPost(blog, blogId);
        }
        else if (!Objects.requireNonNull(image.getContentType()).startsWith("image/")) {
            throw new IllegalArgumentException("File must be an image");
        }

        String imageUrl = s3Service.uploadFile(image);

        if (blog.getImageUrl() != null && !blog.getImageUrl().contains("googleusercontent.com")) {
            s3Service.removeImage(blog.getImageUrl());
        }

        blog.setImageUrl(imageUrl);

        return updateBlogPost(blog, blogId);
    }

    @Override
    public void deleteBlogPost(UUID blogId) {
        if (!blogRepository.existsById(blogId)) {
            throw new EntityNotFoundException("Blog post not found");
        }

        blogRepository.deleteById(blogId);
    }
}
