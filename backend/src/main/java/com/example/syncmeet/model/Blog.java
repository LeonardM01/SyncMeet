package com.example.syncmeet.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "blog")
@Data
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "type")
    private String type;
}
