package com.example.syncmeet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
    @SequenceGenerator(name = "global_id_sequence", sequenceName = "global_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "content")
    private String content;

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;*/
}
