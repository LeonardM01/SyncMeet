package com.example.syncmeet.service;

import com.example.syncmeet.dto.EventDTO;
import com.example.syncmeet.model.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Service Interface for managing {@link Event}
 */
public interface EventService {

    EventDTO toDTO(Event event);

    Event toEntity(EventDTO eventDTO);

    EventDTO getEventById(UUID id);

    List<EventDTO> getActiveEventsByStartDateBetween(LocalDateTime start, LocalDateTime end);

    List<EventDTO> getPendingEventsByStartDateBetween(LocalDateTime start, LocalDateTime end);

    List<EventDTO> getPendingEventsByUserAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end);

    List<EventDTO> getActiveEventsByUserAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end);

    List<EventDTO> getPendingEventsByUser(UUID id);

    List<EventDTO> getActiveEventsByUser(UUID id);

    void addUserToEvent(UUID userId, UUID eventId);

    void removeUserFromEvent(UUID userId, UUID eventId);

    void acceptEvent(UUID id);

    EventDTO createEvent(EventDTO eventDTO);

    EventDTO updateEvent(EventDTO eventDTO, UUID id);

    void deleteEvent(UUID id);
}
