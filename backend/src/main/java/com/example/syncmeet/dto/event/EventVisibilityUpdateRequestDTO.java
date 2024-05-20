package com.example.syncmeet.dto.event;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing an event visibility change
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventVisibilityUpdateRequestDTO {
    @NotNull(message = "Visibility is required")
    private boolean visible;
}
