package com.example.syncmeet.repository;

import com.example.syncmeet.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for {@link Event}
 */
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e WHERE e.startDateTime BETWEEN :startDate AND :endDate AND e.pending = false")
    List<Event> findEventsByStartDateTimeBetweenAndPendingIsFalse(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT e FROM Event e WHERE e.startDateTime BETWEEN :startDate AND :endDate AND e.pending = true")
    List<Event> findEventsByStartDateTimeBetweenAndPendingIsTrue(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT e FROM Event e JOIN e.users u WHERE u.id = :userId AND e.pending = false ")
    List<Event> findByUserIdAndPendingFalse(UUID userId);

    @Query("SELECT e FROM Event e JOIN e.users u WHERE u.id = :userId AND e.pending = true")
    List<Event> findByUserIdAndPendingTrue(UUID userId);

    @Query("SELECT e FROM Event e JOIN e.users u WHERE u.id = :userId AND e.startDateTime BETWEEN :startDate AND :endDate AND e.pending = true")
    List<Event> findEventsByUserIdAndStartDateTimeBetweenAndPendingIsTrue(UUID userId,LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT e FROM Event e JOIN e.users u WHERE u.id = :userId AND e.startDateTime BETWEEN :startDate AND :endDate AND e.pending = false")
    List<Event> findEventsByUserIdAndStartDateTimeBetweenAndPendingIsFalse(UUID userId,LocalDateTime startDate, LocalDateTime endDate);
}
