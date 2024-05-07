package com.example.syncmeet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/**
 * Event request entity
 */
@Entity
@Table(name = "event_requests")
@Data
public class EventRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "pending")
    private boolean pending;
}
