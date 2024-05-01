package com.example.syncmeet.controller;

import com.example.syncmeet.dto.*;
import com.example.syncmeet.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST controller for managing {@link com.example.syncmeet.model.Event}
 */
@RestController
public class EventController {

    private final EventService eventService;


    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * {@code POST /api/event} : Create a new event
     *
     * @param event Event to be created
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and a message that the event was created successfully,
     * or with status {@code 400 (Bad Request)} if the event is invalid
     */
    @PostMapping("/api/event")
    public ResponseEntity<Map<String, Object>> createEvent(@Valid @RequestBody EventDTO event) {
        eventService.createEvent(event);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event created successfully");
        return ResponseEntity.ok(response);
    }

    /**
     * {@code GET /api/event/active} : Fetch all active events between two dates
     *
     * @param startDate Start date of the interval
     * @param endDate End date of the interval
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all active events as {@link EventDTO}s,
     * or with status {@code 400 (Bad request)} if endDate is before startDate
     */
    @GetMapping("/api/event/active")
    public ResponseEntity<List<EventDTO>> getActiveEventsByStartDateBetween(
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return ResponseEntity.ok(eventService.getActiveEventsByStartDateBetween(startDate, endDate));
    }

    /**
     * {@code GET /api/event/pending} : Fetch all pending events between two dates
     *
     * @param startDate Start date of the interval
     * @param endDate End date of the interval
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all pending events as {@link EventDTO}s,
     * or with status {@code 400 (Bad request)} if endDate is before startDate
     */
    @GetMapping("/api/event/pending")
    public ResponseEntity<List<EventDTO>> getPendingEventsByStartDateBetween(
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return ResponseEntity.ok(eventService.getPendingEventsByStartDateBetween(startDate, endDate));
    }

    /**
     * {@code GET /api/event/pending/{id}} : Fetch all pending events of a user
     *
     * @param id ID of the user
     * @param startDate Start date of the interval
     * @param endDate End date of the interval
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all pending events of the user as {@link EventDTO}s,
     * or with status {@code 400 (Bad request)} if endDate is before startDate,
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/api/event/pending/{id}")
    public ResponseEntity<List<EventDTO>> getPendingEventsByUser(
            @PathVariable UUID id,
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<EventDTO> events;

        if (startDate != null && endDate != null) {
            events = eventService.getPendingEventsByUserAndStartDateBetween(id, startDate, endDate);
        }
        else {
            events = eventService.getPendingEventsByUser(id);
        }

        return ResponseEntity.ok(events);
    }

    /**
     * {@code GET /api/event/active/{id}} : Fetch all active events of a user
     * @param id ID of the user
     * @param startDate Start date of the interval
     * @param endDate End date of the interval
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all active events of the user as {@link EventDTO}s,
     * or with status {@code 400 (Bad request)} if endDate is before startDate,
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/api/event/active/{id}")
    public ResponseEntity<List<EventDTO>> getActiveEventsByUser(
            @PathVariable UUID id,
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<EventDTO> events;

        if (startDate != null && endDate != null) {
            events = eventService.getActiveEventsByUserAndStartDateBetween(id, startDate, endDate);
        }
        else {
            events = eventService.getActiveEventsByUser(id);
        }

        return ResponseEntity.ok(events);
    }

    /**
     * {@code GET /api/event/{id}} : Fetch event by ID
     * @param id ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist
     */
    @GetMapping("/api/event/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    /**
     * {@code PUT /api/event/{eventId}/user/{userId}/add} : Add user to event
     * @param userId ID of the user to be added
     * @param eventId ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and a message that the user was added to the event,
     * or with status {@code 404 (Not Found)} if the user or the event does not exist
     */
    @PutMapping("/api/event/{eventId}/user/{userId}/add")
    public ResponseEntity<Map<String, Object>> addUserToEvent(
            @PathVariable UUID userId,
            @PathVariable UUID eventId
    ) {
        eventService.addUserToEvent(userId, eventId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User added to event");
        return ResponseEntity.ok(response);
    }

    /**
     * {@code PUT /api/event/{eventId}/user/{userId}/remove} : Remove user from event
     * @param userId ID of the user to be removed
     * @param eventId ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and a message that the user was removed from the event,
     * or with status {@code 404 (Not Found)} if the user or the event does not exist
     */
    @PutMapping("/api/event/{eventId}/user/{userId}/remove")
    public ResponseEntity<Map<String, Object>> removeUserFromEvent(
            @PathVariable UUID userId,
            @PathVariable UUID eventId
    ) {
        eventService.removeUserFromEvent(userId, eventId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User removed from event");
        return ResponseEntity.ok(response);
    }

    /**
     * {@code PUT /api/event/name/{id}} : Update event name
     * @param id ID of the event
     * @param eventNameUpdateRequest New name of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the updated event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/api/event/name/{id}")
    public ResponseEntity<EventDTO> updateName(
            @PathVariable UUID id,
            @Valid @RequestBody EventNameUpdateRequestDTO eventNameUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setName(eventNameUpdateRequest.getName());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    /**
     * {@code PUT /api/event/description/{id}} : Update event description
     * @param id ID of the event
     * @param eventDescriptionUpdateRequest New description of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the updated event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/api/event/description/{id}")
    public ResponseEntity<EventDTO> updateDescription(
            @PathVariable UUID id,
            @Valid @RequestBody EventDescriptionUpdateRequestDTO eventDescriptionUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setDescription(eventDescriptionUpdateRequest.getDescription());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    /**
     * {@code PUT /api/event/start/{id}} : Update event color
     * @param id ID of the event
     * @param eventColorUpdateRequest New color of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the updated event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/api/event/color/{id}")
    public ResponseEntity<EventDTO> updateColor(
            @PathVariable UUID id,
            @Valid @RequestBody EventColorUpdateRequestDTO eventColorUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setColor(eventColorUpdateRequest.getColor());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    /**
     * {@code PUT /api/event/start/{id}} : Update event visibility
     * @param id ID of the event
     * @param eventVisibleUpdateRequest New visibility of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the updated event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/api/event/visible/{id}")
    public ResponseEntity<EventDTO> updateVisibility(
            @PathVariable UUID id,
            @Valid @RequestBody EventVisibleUpdateRequestDTO eventVisibleUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setVisible(eventVisibleUpdateRequest.isVisible());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    /**
     * {@code PUT /api/event/start/{id}} : Update event recurrence
     * @param id ID of the event
     * @param eventRecurringUpdateRequest New recurrence of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the updated event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/api/event/recurring/{id}")
    public ResponseEntity<EventDTO> updateRecurrence(
            @PathVariable UUID id,
            @Valid @RequestBody EventRecurringUpdateRequestDTO eventRecurringUpdateRequest
    ) {
        EventDTO event = eventService.getEventById(id);
        event.setRecurring(eventRecurringUpdateRequest.isRecurring());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    /**
     * {@code PUT /api/event/start/{id}} : Accept event
     * @param id ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and a message that the event was accepted,
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the event is already accepted
     */
    @PutMapping("/api/event/accept/{id}")
    public ResponseEntity<Map<String, Object>> acceptEvent(@PathVariable UUID id) {
        eventService.acceptEvent(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event accepted");
        return ResponseEntity.ok(response);
    }

    /**
     * {@code DELETE /api/event/{id}} : Reject event OR Remove event
     * @param id ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and a message that the event was rejected or removed,
     * or with status {@code 404 (Not Found)} if the event does not exist,
     */
    @DeleteMapping("/api/event/{id}")
    public ResponseEntity<Map<String, Object>> rejectEvent(@PathVariable UUID id) {
        EventDTO event = eventService.getEventById(id);
        Map<String, Object> response = new HashMap<>();
        // If event is pending, then it is rejected
        if (event.isPending()) {
            response.put("message", "Event rejected");
        }
        // If it was accepted before, then it is removed
        else {
            response.put("message", "Event removed");
        }
        eventService.deleteEvent(id);
        return ResponseEntity.ok(response);
    }
}
