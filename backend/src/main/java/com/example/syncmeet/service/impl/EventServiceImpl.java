package com.example.syncmeet.service.impl;

import com.example.syncmeet.dto.EventDTO;
import com.example.syncmeet.error.exception.EntityNotFoundException;
import com.example.syncmeet.error.exception.UserEventMembershipException;
import com.example.syncmeet.model.Event;
import com.example.syncmeet.model.User;
import com.example.syncmeet.repository.EventRepository;
import com.example.syncmeet.repository.UserRepository;
import com.example.syncmeet.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Event}
 */
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EventDTO toDTO(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }

    @Override
    public Event toEntity(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }

    @Override
    public EventDTO getEventById(UUID id) {
        return eventRepository.findById(id).map(this::toDTO).
                orElseThrow(() -> new EntityNotFoundException("Couldn't find event."));
    }

    @Override
    public List<EventDTO> getActiveEventsByStartDateBetween(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findEventsByStartDateTimeBetweenAndPendingIsFalse(start, end)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getPendingEventsByStartDateBetween(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findEventsByStartDateTimeBetweenAndPendingIsTrue(start, end)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getPendingEventsByUserAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end) {
        return eventRepository.findEventsByUserIdAndStartDateTimeBetweenAndPendingIsTrue(id, start, end)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getActiveEventsByUserAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end) {
        return eventRepository.findEventsByUserIdAndStartDateTimeBetweenAndPendingIsFalse(id, start, end)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getPendingEventsByUser(UUID id) {
        return eventRepository.findByUserIdAndPendingTrue(id)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getActiveEventsByUser(UUID id) {
        return eventRepository.findByUserIdAndPendingFalse(id)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public void addUserToEvent(UUID userId, UUID eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new EntityNotFoundException("Event not found"));
        User user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User not found"));

        if (event.getUsers().stream().anyMatch(u -> u.getId().equals(userId))) {
            throw new UserEventMembershipException("User is already in this event");
        }

        event.getUsers().add(user);
        user.getEvents().add(event);

        eventRepository.save(event);
        userRepository.save(user);
    }

    @Override
    public void removeUserFromEvent(UUID userId, UUID eventId) {
        EventDTO event = getEventById(eventId);
        if (event.getUsers().stream().noneMatch(u -> u.getId().equals(userId))) {
            throw new UserEventMembershipException("User isn't part of this event");
        }
        event.getUsers().removeIf(user -> user.getId().equals(userId));
        updateEvent(event, eventId);
    }

    @Override
    public void acceptEvent(UUID id) {
        EventDTO event = getEventById(id);
        event.setPending(false);
        eventRepository.save(toEntity(event));
    }

    @Override
    public EventDTO createEvent(EventDTO event) {
        return toDTO(eventRepository.save(toEntity(event)));
    }

    @Override
    public EventDTO updateEvent(EventDTO event, UUID id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException("Event not found");
        }

        return createEvent(event);
    }

    @Override
    public void deleteEvent(UUID id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException("Event not found");
        }

        eventRepository.deleteById(id);
    }
}
