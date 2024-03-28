package com.example.syncmeet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long id;

    @NotNull(message = "Start date for event is required")
    private LocalDateTime startDateTime;

    @NotNull(message = "End date for event is required")
    private LocalDateTime endDateTime;

    @Size(min = 3, message = "Event name is too short")
    @Size(max = 255, message = "Event name is too long")
    private String name;

    private String description;

    private String color;

    @NotNull(message = "The visibility of the event is required")
    private boolean visible;

    @NotNull(message = "The recurrance of the event is required")
    private boolean recurring;

    private boolean pending;
}
