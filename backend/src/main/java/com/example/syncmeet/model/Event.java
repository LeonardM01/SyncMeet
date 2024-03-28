package com.example.syncmeet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Data
public class Event {

    @Id
    @SequenceGenerator(
            name = "event_generator",
            sequenceName = "event_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
    private Long id;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "recurring")
    private boolean recurring;

    @Column(name = "pending")
    private boolean pending;
}
