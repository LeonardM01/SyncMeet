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
    /**
     * Convert an Event entity to a DTO
     * @param event the entity to convert
     * @return the DTO
     */
    EventDTO toDTO(Event event);

    /**
     * Convert an Event DTO to an entity
     * @param eventDTO the DTO to convert
     * @return the entity
     */
    Event toEntity(EventDTO eventDTO);

    /**
     * Get an event by its id
     * @param id the id of the event
     * @return the event
     */
    EventDTO getEventById(UUID id);

    /**
     * Get all active events that start between two dates
     * @param start the start date
     * @param end the end date
     * @return the list of events
     */
    List<EventDTO> getActiveEventsByStartDateBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Get all pending events that start between two dates
     * @param start the start date
     * @param end the end date
     * @return the list of events
     */
    List<EventDTO> getPendingEventsByStartDateBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Get all pending events that a user is a part of and start between two dates
     * @param id the id of the user
     * @param start the start date
     * @param end the end date
     * @return the list of events
     */
    List<EventDTO> getPendingEventsByUserAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end);

    /**
     * Get all active events that a user is a part of and start between two dates
     * @param id the id of the user
     * @param start the start date
     * @param end the end date
     * @return the list of events
     */
    List<EventDTO> getActiveEventsByUserAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end);

    /**
     * Get all pending events that a user is a part of
     * @param id the id of the user
     * @return the list of events
     */
    List<EventDTO> getPendingEventsByUser(UUID id);

    /**
     * Get all active events that a user is a part of
     * @param id the id of the user
     * @return the list of events
     */
    List<EventDTO> getActiveEventsByUser(UUID id);

    /**
     * Add a user to an event
     * @param userId the id of the user
     * @param eventId the id of the event
     */
    void addUserToEvent(UUID userId, UUID eventId);

    /**
     * Remove a user from an event
     * @param userId the id of the user
     * @param eventId the id of the event
     */
    void removeUserFromEvent(UUID userId, UUID eventId);

    /**
     * Accept an event
     * @param id the id of the event
     */
    void acceptEvent(UUID id);

    /**
     * Create an event
     * @param eventDTO the event to create
     * @return the created event
     */
    EventDTO createEvent(EventDTO eventDTO);

    /**
     * Update an event
     * @param eventDTO the updated event
     * @param id the id of the event
     * @return the updated event
     */
    EventDTO updateEvent(EventDTO eventDTO, UUID id);

    /**
     * Delete an event
     * @param id the id of the event
     */
    void deleteEvent(UUID id);
}
