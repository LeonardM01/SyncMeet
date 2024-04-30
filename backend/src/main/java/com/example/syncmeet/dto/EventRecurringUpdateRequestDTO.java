package com.example.syncmeet.dto;

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

    private boolean recurring;
}
