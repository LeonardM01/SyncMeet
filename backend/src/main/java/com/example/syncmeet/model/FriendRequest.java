package com.example.syncmeet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "friend_requests")
@Data
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
    @SequenceGenerator(name = "global_id_sequence", sequenceName = "global_id_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    @Column(name = "pending_request")
    private boolean pendingRequest;
}
