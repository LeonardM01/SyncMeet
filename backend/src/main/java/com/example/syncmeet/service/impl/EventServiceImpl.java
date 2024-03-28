package com.example.syncmeet.service.impl;

import com.example.syncmeet.dto.EventDTO;
import com.example.syncmeet.error.exception.EntityNotFoundException;
import com.example.syncmeet.model.Event;
import com.example.syncmeet.repository.EventRepository;
import com.example.syncmeet.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
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
    public EventDTO getEventById(Long id) {
        return eventRepository.findById(id).map(this::toDTO).
                orElseThrow(() -> new EntityNotFoundException("Couldn't find event."));
    }

    @Override
    public EventDTO getEventByStartDateBetween(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findEventByStartDateTimeBetween(start, end).map(this::toDTO).
                orElseThrow(() -> new EntityNotFoundException("Couldn't find event with start date between specified time"));
    }

    @Override
    public EventDTO createEvent(EventDTO event) {
        return toDTO(eventRepository.save(toEntity(event)));
    }

    @Override
    public EventDTO updateEvent(EventDTO event, Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }

        return createEvent(event);
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException("Event not found");
        }

        eventRepository.deleteById(id);
    }
}
