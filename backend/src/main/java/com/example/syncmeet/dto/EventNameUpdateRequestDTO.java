package com.example.syncmeet.dto;

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

    @Size(min = 3, message = "Event name is too short")
    @Size(max = 255, message = "Event name is too long")
    private String name;
}
