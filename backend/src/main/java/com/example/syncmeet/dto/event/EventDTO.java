package com.example.syncmeet.dto.event;

import com.example.syncmeet.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for the {@link com.example.syncmeet.model.Event} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private UUID id;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String name;

    private String description;

    private String color;

    private boolean visible;

    private boolean recurring;

    private UserDTO owner;

    private Set<UserDTO> users;
}
