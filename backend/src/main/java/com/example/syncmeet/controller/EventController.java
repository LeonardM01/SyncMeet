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

    @PostMapping("/api/event")
    public ResponseEntity<Map<String, Object>> createEvent(@Valid @RequestBody EventDTO event) {
        eventService.createEvent(event);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event created successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/event/active")
    public ResponseEntity<List<EventDTO>> getActiveEventsByStartDateBetween(
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return ResponseEntity.ok(eventService.getActiveEventsByStartDateBetween(startDate, endDate));
    }

    @GetMapping("/api/event/pending")
    public ResponseEntity<List<EventDTO>> getPendingEventsByStartDateBetween(
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return ResponseEntity.ok(eventService.getPendingEventsByStartDateBetween(startDate, endDate));
    }

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

    @GetMapping("/api/event/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

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

    @PutMapping("/api/event/name/{id}")
    public ResponseEntity<EventDTO> updateName(
            @PathVariable UUID id,
            @Valid @RequestBody EventNameUpdateRequestDTO eventNameUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setName(eventNameUpdateRequest.getName());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("/api/event/description/{id}")
    public ResponseEntity<EventDTO> updateDescription(
            @PathVariable UUID id,
            @RequestBody EventDescriptionUpdateRequestDTO eventDescriptionUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setDescription(eventDescriptionUpdateRequest.getDescription());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("/api/event/color/{id}")
    public ResponseEntity<EventDTO> updateColor(
            @PathVariable UUID id,
            @RequestBody EventColorUpdateRequestDTO eventColorUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setColor(eventColorUpdateRequest.getColor());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("/api/event/visible/{id}")
    public ResponseEntity<EventDTO> updateVisibility(
            @PathVariable UUID id,
            @RequestBody EventVisibleUpdateRequestDTO eventVisibleUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setVisible(eventVisibleUpdateRequest.isVisible());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("/api/event/recurring/{id}")
    public ResponseEntity<EventDTO> updateRecurrence(
            @PathVariable UUID id,
            @RequestBody EventRecurringUpdateRequestDTO eventRecurringUpdateRequest
    ) {
        EventDTO event = eventService.getEventById(id);
        event.setRecurring(eventRecurringUpdateRequest.isRecurring());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("/api/event/accept/{id}")
    public ResponseEntity<Map<String, Object>> acceptEvent(@PathVariable UUID id) {
        eventService.acceptEvent(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event accepted");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/event/{id}")
    public ResponseEntity<Map<String, Object>> rejectEvent(@PathVariable UUID id) {
        EventDTO event = eventService.getEventById(id);
        Map<String, Object> response = new HashMap<>();
        if (event.isPending()) {
            response.put("message", "Event rejected");
        }
        else {
            response.put("message", "Event removed");
        }
        eventService.deleteEvent(id);
        return ResponseEntity.ok(response);
    }
}
