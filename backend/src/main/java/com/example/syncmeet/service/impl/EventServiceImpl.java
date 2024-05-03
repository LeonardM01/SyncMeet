package com.example.syncmeet.service.impl;

import com.example.syncmeet.dto.EventDTO;
import com.example.syncmeet.dto.EventRequestDTO;
import com.example.syncmeet.dto.UserDTO;
import com.example.syncmeet.error.exception.EntityNotFoundException;
import com.example.syncmeet.error.exception.RequestException;

import com.example.syncmeet.error.exception.InvalidDateOrderException;
import com.example.syncmeet.error.exception.UserEventMembershipException;
import com.example.syncmeet.model.Event;
import com.example.syncmeet.model.EventRequest;
import com.example.syncmeet.repository.EventRepository;
import com.example.syncmeet.repository.EventRequestRepository;
import com.example.syncmeet.repository.UserRepository;
import com.example.syncmeet.service.EventService;
import com.example.syncmeet.service.UserService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Event}
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventRequestRepository eventRequestRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, EventRequestRepository eventRequestRepository, UserService userService, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventRequestRepository = eventRequestRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        configureModelMapper();
    }

    /**
     * Configures the ModelMapper to map the users of an event to the EventDTO
     */
    private void configureModelMapper() {
        TypeMap<Event, EventDTO> eventTypeMap = modelMapper.getTypeMap(Event.class, EventDTO.class);
        if (eventTypeMap == null) {
            eventTypeMap = modelMapper.createTypeMap(Event.class, EventDTO.class);
        }
        eventTypeMap.setPostConverter(eventToDtoConverter());
    }

    private Converter<Event, EventDTO> eventToDtoConverter() {
        return context -> {
            Event source = context.getSource();
            EventDTO destination = context.getDestination();
            mapEventUsers(source, destination);
            return destination;
        };
    }

    /**
     * Maps the users of an event to the EventDTO
     * @param event The event to map
     * @param eventDTO The EventDTO to map to
     */
    private void mapEventUsers(Event event, EventDTO eventDTO) {
        List<UserDTO> users = userRepository.findByEventIdAndPendingFalse(event.getId())
                .stream().map(userService::userToDTO).distinct().toList();
        eventDTO.setUsers(Set.copyOf(users));
    }

    @Override
    public EventDTO eventToDTO(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }

    @Override
    public Event eventToEntity(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }

    @Override
    public EventRequestDTO eventRequestToDTO(EventRequest eventRequest) {
        return modelMapper.map(eventRequest, EventRequestDTO.class);
    }

    @Override
    public EventRequest eventRequestToEntity(EventRequestDTO eventRequestDTO) {
        return modelMapper.map(eventRequestDTO, EventRequest.class);
    }

    @Override
    public EventDTO getEventById(UUID id) {
        return eventRepository.findById(id).map(this::eventToDTO).
                orElseThrow(() -> new EntityNotFoundException("Couldn't find event."));
    }

    @Override
    public EventRequestDTO getEventRequestByUserIdAndEventId(UUID userId, UUID eventId) {
        return eventRequestRepository.findByUserIdAndEventId(userId, eventId)
                .map(this::eventRequestToDTO).orElseThrow(() ->
                        new EntityNotFoundException("Event request not found"));
    }

    @Override
    public List<EventDTO> getEventsByOwner(UUID id) {
        return eventRepository.findByOwnerId(id)
                .stream().map(this::eventToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getPendingEventsByOwner(UUID id) {
        return eventRepository.findEventsByOwnerIdAndPendingIsTrue(id)
                .stream().map(this::eventToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getActiveEventsByOwner(UUID id) {
        return eventRepository.findEventsByOwnerIdAndPendingIsFalse(id)
                .stream().map(this::eventToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getPendingEventsByOwnerAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end) {
        return eventRepository.findEventsByOwnerIdAndStartDateBetweenAndPendingIsTrue(id, start, end)
                .stream().map(this::eventToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getActiveEventsByOwnerAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end) {
        return eventRepository.findEventsByOwnerIdAndStartDateBetweenAndPendingIsFalse(id, start, end)
                .stream().map(this::eventToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getEventsByUser(UUID id) {
        return eventRepository.findByUserId(id).stream().map(this::eventToDTO).collect(Collectors.toList());

    public List<EventDTO> getActiveEventsByStartDateBetween(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) throw new InvalidDateOrderException("Start date cannot be after end date");

        return eventRepository.findEventsByStartDateTimeBetweenAndPendingIsFalse(start, end)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getPendingEventsByStartDateBetween(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) throw new InvalidDateOrderException("Start date cannot be after end date");

        return eventRepository.findEventsByStartDateTimeBetweenAndPendingIsTrue(start, end)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getPendingEventsByUserAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) throw new InvalidDateOrderException("Start date cannot be after end date");

        return eventRepository.findEventsByUserIdAndStartDateTimeBetweenAndPendingIsTrue(id, start, end)
                .stream().map(this::eventToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getActiveEventsByUserAndStartDateBetween(UUID id, LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) throw new InvalidDateOrderException("Start date cannot be after end date");

        return eventRepository.findEventsByUserIdAndStartDateTimeBetweenAndPendingIsFalse(id, start, end)
                .stream().map(this::eventToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getPendingEventsByUser(UUID id) {
        return eventRepository.findByUserIdAndPendingTrue(id)
                .stream().map(this::eventToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getActiveEventsByUser(UUID id) {
        return eventRepository.findByUserIdAndPendingFalse(id)
                .stream().map(this::eventToDTO).collect(Collectors.toList());
    }

    @Override
    public void createEventRequest(UUID userId, UUID eventId) {
        EventDTO event = eventRepository.findById(eventId)
                .map(this::eventToDTO).orElseThrow(() ->
                    new EntityNotFoundException("Event not found"));
        UserDTO user = userService.getUserById(userId);

        // Throw exception if user is already in event or owner
        if (event.getUsers().stream().anyMatch(u -> u.getId().equals(userId)) || event.getOwner().getId().equals(userId)) {
            throw new UserEventMembershipException("User is already in this event");
        }

        if (eventRequestRepository.findByUserIdAndEventId(userId, eventId).isPresent()) {
            throw new RequestException("Event request already exists");
        }

        EventRequestDTO eventRequest = new EventRequestDTO();
        eventRequest.setUser(user);
        eventRequest.setEvent(event);
        eventRequest.setPending(true);

        eventRequestRepository.save(eventRequestToEntity(eventRequest));
    }

    @Override
    public void acceptEvent(UUID userId, UUID eventId) {
        EventRequestDTO eventRequest = getEventRequestByUserIdAndEventId(userId, eventId);

        if (!eventRequest.isPending()) {
            throw new RequestException("Event request is already accepted");
        }

        eventRequest.setPending(false);
        eventRequestRepository.save(eventRequestToEntity(eventRequest));
    }

    @Override
    public EventDTO createEvent(EventDTO event, UUID ownerId) {
        event.setOwner(userService.getUserById(ownerId));
        return eventToDTO(eventRepository.save(eventToEntity(event)));
    }

    @Override
    public EventDTO updateEvent(EventDTO event, UUID id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException("Event not found");
        }

        return eventToDTO(eventRepository.save(eventToEntity(event)));
    }

    @Override
    public void deleteEvent(UUID id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException("Event not found");
        }

        eventRepository.deleteById(id);
    }

    @Override
    public void deleteEventRequest(UUID userId, UUID eventId) {
        EventDTO event = getEventById(eventId);
        if (event.getUsers().stream().noneMatch(u -> u.getId().equals(userId))) {
            throw new UserEventMembershipException("User isn't part of this event");
        }
        if (event.getOwner().getId().equals(userId)) {
            throw new UserEventMembershipException("Owner can't be removed from event");
        }

        EventRequestDTO eventRequest = getEventRequestByUserIdAndEventId(userId, eventId);
        eventRequestRepository.delete(eventRequestToEntity(eventRequest));
    }
}
