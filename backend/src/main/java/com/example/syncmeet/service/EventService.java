package com.example.syncmeet.service;

import com.example.syncmeet.dto.EventDTO;
import com.example.syncmeet.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    EventDTO toDTO(Event event);

    Event toEntity(EventDTO eventDTO);

    EventDTO getEventById(Long id);

    List<EventDTO> getActiveEventsByStartDateBetween(LocalDateTime start, LocalDateTime end);

    List<EventDTO> getPendingEventsByStartDateBetween(LocalDateTime start, LocalDateTime end);

    void acceptEvent(Long id);

    EventDTO createEvent(EventDTO eventDTO);

    EventDTO updateEvent(EventDTO eventDTO, Long id);

    void deleteEvent(Long id);
}
