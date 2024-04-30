package com.example.syncmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing an event visibility change
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventVisibleUpdateRequestDTO {

    private boolean visible;
}
