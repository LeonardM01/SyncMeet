package com.example.syncmeet.service;

import com.example.syncmeet.dto.EventDTO;
import com.example.syncmeet.model.Event;

import java.time.LocalDateTime;

public interface EventService {

    EventDTO toDTO(Event event);

    Event toEntity(EventDTO eventDTO);

    EventDTO getEventById(Long id);
    EventDTO getEventByStartDateBetween(LocalDateTime start, LocalDateTime end);

    EventDTO createEvent(EventDTO eventDTO);

    EventDTO updateEvent(EventDTO eventDTO, Long id);

    void deleteEvent(Long id);
}
