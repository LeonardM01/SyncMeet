package com.example.syncmeet.repository;

import com.example.syncmeet.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findEventsByStartDateTimeBetweenAndPendingIsFalse(LocalDateTime startDate, LocalDateTime endDate);

    List<Event> findEventsByStartDateTimeBetweenAndPendingIsTrue(LocalDateTime startDate, LocalDateTime endDate);


}
