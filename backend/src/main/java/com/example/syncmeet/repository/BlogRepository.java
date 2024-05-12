package com.example.syncmeet.repository;

import com.example.syncmeet.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {

    List<Blog> findByAuthorId(UUID id);

    List<Blog> findBlogByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Blog> findByType(String type);
}
