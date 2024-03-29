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

    @GetMapping("/api/event/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("/api/event/name/{id}")
    public ResponseEntity<EventDTO> updateName(
            @PathVariable Long id,
            @Valid @RequestBody EventNameUpdateRequestDTO eventNameUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setName(eventNameUpdateRequest.getName());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("/api/event/description/{id}")
    public ResponseEntity<EventDTO> updateDescription(
            @PathVariable Long id,
            @RequestBody EventDescriptionUpdateRequestDTO eventDescriptionUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setDescription(eventDescriptionUpdateRequest.getDescription());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("/api/event/color/{id}")
    public ResponseEntity<EventDTO> updateColor(
            @PathVariable Long id,
            @RequestBody EventColorUpdateRequestDTO eventColorUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setColor(eventColorUpdateRequest.getColor());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("/api/event/visible/{id}")
    public ResponseEntity<EventDTO> updateVisibility(
            @PathVariable Long id,
            @RequestBody EventVisibleUpdateRequestDTO eventVisibleUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setVisible(eventVisibleUpdateRequest.isVisible());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("/api/event/recurring/{id}")
    public ResponseEntity<EventDTO> updateRecurrence(
            @PathVariable Long id,
            @RequestBody EventRecurringUpdateRequestDTO eventRecurringUpdateRequest
    ) {
        EventDTO event = eventService.getEventById(id);
        event.setRecurring(eventRecurringUpdateRequest.isRecurring());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("/api/event/accept/{id}")
    public ResponseEntity<Map<String, Object>> acceptEvent(@PathVariable Long id) {
        eventService.acceptEvent(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event accepted");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/event/{id}")
    public ResponseEntity<Map<String, Object>> rejectEvent(@PathVariable Long id) {
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
