package com.example.syncmeet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing an event recurrence change
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRecurringUpdateRequestDTO {
    @NotNull(message = "Recurrence is required")
    private boolean recurring;
}
