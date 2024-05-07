package com.example.syncmeet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing an event description change
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDescriptionUpdateRequestDTO {
    @NotNull(message = "Description is required")
    private String description;
}
