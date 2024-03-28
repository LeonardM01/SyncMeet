package com.example.syncmeet.controller;

import com.example.syncmeet.dto.EventDTO;
import com.example.syncmeet.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/api/event/create")
    public ResponseEntity<Map<String, Object>> createEvent(@Valid @RequestBody EventDTO event) {
        eventService.createEvent(event);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event created successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("api/event/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("api/event/update_name/{id}")
    public ResponseEntity<EventDTO> updateName(
            @PathVariable Long id,
            @RequestBody String name
    ) {
        EventDTO event = eventService.getEventById(id);
        event.setName(name);
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("api/event/update_desc/{id}")
    public ResponseEntity<EventDTO> updateDescription(
            @PathVariable Long id,
            @RequestBody String desc
    ) {
        EventDTO event = eventService.getEventById(id);
        event.setDescription(desc);
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("api/event/update_color/{id}")
    public ResponseEntity<EventDTO> updateColor(
            @PathVariable Long id,
            @RequestBody String color
    ) {
        EventDTO event = eventService.getEventById(id);
        event.setColor(color);
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("api/event/update_visible/{id}")
    public ResponseEntity<EventDTO> updateVisibility(
            @PathVariable Long id,
            @RequestBody boolean visible
    ) {
        EventDTO event = eventService.getEventById(id);
        event.setVisible(visible);
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("api/event/update_recurring/{id}")
    public ResponseEntity<EventDTO> updateRecurrance(
            @PathVariable Long id,
            @RequestBody boolean recurring
    ) {
        EventDTO event = eventService.getEventById(id);
        event.setRecurring(recurring);
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    @PutMapping("api/event/update_pending/{id}")
    public ResponseEntity<EventDTO> updatePending(
            @PathVariable Long id,
            @RequestBody boolean pending
    ) {
        EventDTO event = eventService.getEventById(id);
        event.setPending(pending);
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }
}
