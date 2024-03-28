package com.example.syncmeet.repository;

import com.example.syncmeet.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findEventByStartDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
