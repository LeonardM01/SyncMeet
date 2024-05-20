package com.example.syncmeet.dto.event;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing an event color change
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventColorUpdateRequestDTO {
    @NotNull(message = "Color is required")
    private String color;
}
