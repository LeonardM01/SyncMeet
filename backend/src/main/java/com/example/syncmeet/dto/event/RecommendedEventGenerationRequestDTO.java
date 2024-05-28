package com.example.syncmeet.dto.event;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing a request to generate a recommended event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendedEventGenerationRequestDTO {
    @NotNull(message = "Name is required")
    private String name;
}
