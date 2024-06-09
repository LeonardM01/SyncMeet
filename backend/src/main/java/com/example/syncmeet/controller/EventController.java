package com.example.syncmeet.controller;

import com.example.syncmeet.dto.event.*;
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
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "https://syncmeet.space" } , allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EventController {

    private final EventService eventService;


    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * {@code POST /event} : Create a new event
     *
     * @param eventCreationRequestDTO the event to create
     * @param ownerId ID of the owner of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the created event as {@link EventDTO},
     * or with status {@code 400 (Bad Request)} if the event is invalid
     */
    @PostMapping("/event/{ownerId}")
    public ResponseEntity<EventDTO> createEvent(
            @Valid @RequestBody EventCreationRequestDTO eventCreationRequestDTO,
            @PathVariable UUID ownerId
    ) {
        EventDTO event = new EventDTO();
        event.setStartDateTime(eventCreationRequestDTO.getStartDateTime());
        event.setEndDateTime(eventCreationRequestDTO.getEndDateTime());
        event.setName(eventCreationRequestDTO.getName());
        event.setDescription(eventCreationRequestDTO.getDescription());
        event.setColor(eventCreationRequestDTO.getColor());
        event.setVisible(eventCreationRequestDTO.isVisible());
        event.setRecurring(eventCreationRequestDTO.isRecurring());

        return ResponseEntity.ok(eventService.createEvent(event, ownerId));
    }

    /**
     * {@code GET /event/owner/{id}} : Fetch all events where a user is the owner
     *
     * @param id ID of the user
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all events of the user as {@link EventDTO}s,
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/event/owner/{id}")
    public ResponseEntity<List<EventDTO>> getEventsByOwner(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getEventsByOwner(id));
    }

    /**
     * {@code GET /event/pending/owner/{id}} : Fetch all pending events where a user is the owner
     *
     * @param id ID of the user
     * @param startDate Start date of the interval
     * @param endDate End date of the interval
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all pending events of the user as {@link EventDTO}s,
     * or with status {@code 400 (Bad request)} if endDate is before startDate,
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/event/pending/owner/{id}")
    public ResponseEntity<List<EventDTO>> getPendingEventsByOwnerAndStartDateBetween(
            @PathVariable UUID id,
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<EventDTO> events;

        if (startDate != null && endDate != null) {
            events = eventService.getPendingEventsByOwnerAndStartDateBetween(id, startDate, endDate);
        }
        else {
            events = eventService.getPendingEventsByOwner(id);
        }

        return ResponseEntity.ok(events);
    }

    /**
     * {@code GET /event/active/owner/{id}} : Fetch all active events where a user is the owner
     *
     * @param id ID of the user
     * @param startDate Start date of the interval
     * @param endDate End date of the interval
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all active events of the user as {@link EventDTO}s,
     * or with status {@code 400 (Bad request)} if endDate is before startDate,
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/event/active/owner/{id}")
    public ResponseEntity<List<EventDTO>> getActiveEventsByOwnerAndStartDateBetween(
            @PathVariable UUID id,
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<EventDTO> events;

        if (startDate != null && endDate != null) {
            events = eventService.getActiveEventsByOwnerAndStartDateBetween(id, startDate, endDate);
        }
        else {
            events = eventService.getActiveEventsByOwner(id);
        }

        return ResponseEntity.ok(events);
    }

    /**
     * {@code GET /event/user/{id}} : Fetch all events of a user
     *
     * @param id ID of the user
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all events of the user as {@link EventDTO}s,
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/event/user/{id}")
    public ResponseEntity<List<EventDTO>> getEventsByUser(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getEventsByUser(id));
    }

    /**
     * {@code GET /event/pending/{id}} : Fetch all pending events of a user
     *
     * @param id ID of the user
     * @param startDate Start date of the interval
     * @param endDate End date of the interval
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all pending events of the user as {@link EventDTO}s,
     * or with status {@code 400 (Bad request)} if endDate is before startDate,
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/event/pending/{id}")
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
     * {@code GET /event/active/{id}} : Fetch all active events of a user
     * @param id ID of the user
     * @param startDate Start date of the interval
     * @param endDate End date of the interval
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all active events of the user as {@link EventDTO}s,
     * or with status {@code 400 (Bad request)} if endDate is before startDate,
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/event/active/{id}")
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
     * {@code GET /event/{id}} : Fetch event by ID
     * @param id ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist
     */
    @GetMapping("/event/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    /**
     * {@code PUT /event/{eventId}/user/{userId}/send} : Send event request
     * @param userId ID of the user to be added
     * @param eventId ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)}
     * and a message that the event request was sent and the event request as {@link EventRequestDTO},
     * or with status {@code 404 (Not Found)} if the user or the event does not exist
     */
    @PutMapping("/event/{eventId}/user/{userId}/send")
    public ResponseEntity<Map<String, Object>> sendEventRequest(
            @PathVariable UUID userId,
            @PathVariable UUID eventId
    ) {
        eventService.createEventRequest(userId, eventId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event request sent");
        return ResponseEntity.ok(response);
    }

    /**
     * {@code PUT /event/{eventId}/user/{userId}/remove} : Remove user from event
     * @param userId ID of the user to be removed
     * @param eventId ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and a message that the user was removed from the event,
     * or with status {@code 404 (Not Found)} if the user or the event does not exist
     */
    @PutMapping("/event/{eventId}/user/{userId}/remove")
    public ResponseEntity<Map<String, Object>> removeUserFromEvent(
            @PathVariable UUID userId,
            @PathVariable UUID eventId
    ) {
        eventService.deleteEventRequest(userId, eventId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User removed from event");
        return ResponseEntity.ok(response);
    }

    /**
     * {@code PUT /event/name/{id}} : Update event name
     * @param id ID of the event
     * @param eventNameUpdateRequest New name of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the updated event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/event/name/{id}")
    public ResponseEntity<EventDTO> updateName(
            @PathVariable UUID id,
            @Valid @RequestBody EventNameUpdateRequestDTO eventNameUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setName(eventNameUpdateRequest.getName());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    /**
     * {@code PUT /event/description/{id}} : Update event description
     * @param id ID of the event
     * @param eventDescriptionUpdateRequest New description of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the updated event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/event/description/{id}")
    public ResponseEntity<EventDTO> updateDescription(
            @PathVariable UUID id,
            @Valid @RequestBody EventDescriptionUpdateRequestDTO eventDescriptionUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setDescription(eventDescriptionUpdateRequest.getDescription());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    /**
     * {@code PUT /event/start/{id}} : Update event color
     * @param id ID of the event
     * @param eventColorUpdateRequest New color of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the updated event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/event/color/{id}")
    public ResponseEntity<EventDTO> updateColor(
            @PathVariable UUID id,
            @Valid @RequestBody EventColorUpdateRequestDTO eventColorUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setColor(eventColorUpdateRequest.getColor());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    /**
     * {@code PUT /event/start/{id}} : Update event visibility
     * @param id ID of the event
     * @param eventVisibilityUpdateRequest New visibility of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the updated event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/event/visible/{id}")
    public ResponseEntity<EventDTO> updateVisibility(
            @PathVariable UUID id,
            @Valid @RequestBody EventVisibilityUpdateRequestDTO eventVisibilityUpdateRequest
            ) {
        EventDTO event = eventService.getEventById(id);
        event.setVisible(eventVisibilityUpdateRequest.isVisible());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    /**
     * {@code PUT /event/start/{id}} : Update event recurrence
     * @param id ID of the event
     * @param eventRecurrenceUpdateRequest New recurrence of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the updated event as {@link EventDTO},
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/event/recurring/{id}")
    public ResponseEntity<EventDTO> updateRecurrence(
            @PathVariable UUID id,
            @Valid @RequestBody EventRecurrenceUpdateRequestDTO eventRecurrenceUpdateRequest
    ) {
        EventDTO event = eventService.getEventById(id);
        event.setRecurring(eventRecurrenceUpdateRequest.isRecurring());
        return ResponseEntity.ok(eventService.updateEvent(event, id));
    }

    /**
     * {@code PUT /event/start/{id}} : Accept event
     * @param userId ID of the user
     * @param eventId ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and a message that the event was accepted,
     * or with status {@code 404 (Not Found)} if the event does not exist,
     * or with status {@code 400 (Bad request} if the event is already accepted
     */
    @PutMapping("/event/accept/{userId}/{eventId}")
    public ResponseEntity<Map<String, Object>> acceptEvent(
            @PathVariable UUID userId,
            @PathVariable UUID eventId
    ) {
        eventService.acceptEvent(userId, eventId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event accepted");
        return ResponseEntity.ok(response);
    }

    /**
     * {@code DELETE /event/{id}} : Reject event OR Remove event
     * @param userId ID of the user
     * @param eventId ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and a message that the event was rejected or removed,
     * or with status {@code 404 (Not Found)} if the event does not exist,
     *
     */
    @DeleteMapping("/event/reject/{userId}/{eventId}")
    public ResponseEntity<Map<String, Object>> rejectEvent(
            @PathVariable UUID userId,
            @PathVariable UUID eventId
    ) {
        EventRequestDTO eventRequest = eventService.getEventRequestByUserIdAndEventId(userId, eventId);
        Map<String, Object> response = new HashMap<>();

        // If event is pending, then it is rejected
        if (eventRequest.isPending()) response.put("message", "Event rejected");
        // If it was accepted before, then it is removed
        else response.put("message", "Event removed");

        eventService.deleteEventRequest(userId, eventId);
        return ResponseEntity.ok(response);
    }

    /**
     * {@code DELETE /event/{id}} : Delete event
     * @param id ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and a message that the event was deleted,
     * or with status {@code 404 (Not Found)} if the event does not exist
     */
    @DeleteMapping("/event/{id}")
    public ResponseEntity<Map<String, Object>> deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event deleted");
        return ResponseEntity.ok(response);
    }
}
