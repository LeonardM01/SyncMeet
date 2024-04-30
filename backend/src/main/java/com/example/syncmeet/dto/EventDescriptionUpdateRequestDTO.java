package com.example.syncmeet.dto;

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

    private String description;
}
