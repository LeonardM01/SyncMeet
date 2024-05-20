package com.example.syncmeet.dto.event;

import com.example.syncmeet.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for the {@link com.example.syncmeet.model.EventRequest} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO {

    private UUID id;

    private UserDTO user;

    private EventDTO event;

    private boolean pending;
}
