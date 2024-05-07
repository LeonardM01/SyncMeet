package com.example.syncmeet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/**
 * Friend request entity
 */
@Entity
@Table(name = "friend_requests")
@Data
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    @Column(name = "pending_request")
    private boolean pendingRequest;
}
