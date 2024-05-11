package com.example.syncmeet.service;

import com.example.syncmeet.dto.event.EventDTO;
import com.example.syncmeet.dto.event.EventRequestDTO;
import com.example.syncmeet.model.Event;
import com.example.syncmeet.model.EventRequest;

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
    EventDTO eventToDTO(Event event);

    /**
     * Convert an Event DTO to an entity
     * @param eventDTO the DTO to convert
     * @return the entity
     */
    Event eventToEntity(EventDTO eventDTO);

    /**
     * Convert an EventRequest entity to a DTO
     * @param eventRequest the entity to convert
     * @return the DTO
     */
    EventRequestDTO eventRequestToDTO(EventRequest eventRequest);

    /**
     * Convert an EventRequest DTO to an entity
     * @param eventRequestDTO the DTO to convert
     * @return the entity
     */
    EventRequest eventRequestToEntity(EventRequestDTO eventRequestDTO);

    /**
     * Get an event by its id
     * @param id the id of the event
     * @return the event
     */
    EventDTO getEventById(UUID id);

    /**
     * Get an event request by its id
     * @param userId the id of the user
     * @param eventId the id of the event
     * @return the event request
     */
    EventRequestDTO getEventRequestByUserIdAndEventId(UUID userId, UUID eventId);

    /**
     * Get all events owned by a user
     * @param id the id of the user
     * @return the list of events
     */
    List<EventDTO> getEventsByOwner(UUID id);

    /**
     * Get all pending events owned by a user
     * @param id the id of the user
     * @return the list of events
     */
    List<EventDTO> getPendingEventsByOwner(UUID id);

    /**
     * Get all active events owned by a user
     * @param id the id of the user
     * @return the list of events
     */
    List<EventDTO> getActiveEventsByOwner(UUID id);

    /**
     * Get all pending events owned by a user and start between two dates
     * @param id the id of the user
     * @param start the start date
     * @param end the end date
     * @return the list of events
     */
    List<EventDTO> getPendingEventsByOwnerAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end);

    /**
     * Get all active events owned by a user and start between two dates
     * @param id the id of the user
     * @param start the start date
     * @param end the end date
     * @return the list of events
     */
    List<EventDTO> getActiveEventsByOwnerAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end);

    /**
     * Get all events that a user is a part of
     * @param id the id of the user
     * @return the list of events
     */
    List<EventDTO> getEventsByUser(UUID id);

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
     * Create an event request
     *
     * @param userId  the id of the user
     * @param eventId the id of the event
     */
    void createEventRequest(UUID userId, UUID eventId);

    /**
     * Accept an event
     * @param userId the id of the user
     * @param eventId the id of the event
     */
    void acceptEvent(UUID userId, UUID eventId);

    /**
     * Create an event
     * @param eventDTO the event to create
     * @param ownerId the id of the owner
     * @return the created event
     */
    EventDTO createEvent(EventDTO eventDTO, UUID ownerId);

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

    /**
     * Delete an event request
     * @param userId the id of the user
     * @param eventId the id of the event
     */
    void deleteEventRequest(UUID userId, UUID eventId);
}
