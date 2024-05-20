package com.example.syncmeet.dto.event;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing an event name change
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventNameUpdateRequestDTO {
    @NotNull(message = "Name is required")
    @Size(min = 3, message = "Event name is too short")
    @Size(max = 255, message = "Event name is too long")
    private String name;
}
