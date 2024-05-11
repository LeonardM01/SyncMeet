package com.example.syncmeet.dto.event;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCreationRequestDTO {

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

    @NotNull(message = "The recurrence of the event is required")
    private boolean recurring;
}
