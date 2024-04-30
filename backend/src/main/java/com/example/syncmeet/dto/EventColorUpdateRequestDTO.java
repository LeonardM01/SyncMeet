package com.example.syncmeet.dto;

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

    private String color;
}
