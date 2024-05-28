package com.example.syncmeet.repository;

import com.example.syncmeet.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for {@link Event}
 */
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e WHERE similarity(e.name, :name) > 0.1 AND e.startDateTime BETWEEN :start AND :end")
    List<Event> findByNameFuzzyAndDateTimeBetween(@Param("name") String name, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    List<Event> findByOwnerId(UUID ownerId);

    @Query("SELECT e FROM Event e JOIN EventRequest er ON e.id = er.event.id WHERE e.owner.id = :ownerId AND er.pending = true")
    List<Event> findEventsByOwnerIdAndPendingIsTrue(@Param("ownerId") UUID ownerId);

    @Query("SELECT e FROM Event e JOIN EventRequest er ON e.id = er.event.id WHERE e.owner.id = :ownerId AND er.pending = false")
    List<Event> findEventsByOwnerIdAndPendingIsFalse(@Param("ownerId") UUID ownerId);

    @Query("SELECT e FROM Event e JOIN EventRequest er ON e.id = er.event.id WHERE e.owner.id = :ownerId AND er.pending = true AND e.startDateTime BETWEEN :startDate AND :endDate")
    List<Event> findEventsByOwnerIdAndStartDateBetweenAndPendingIsTrue(
            @Param("ownerId") UUID ownerId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT e FROM Event e JOIN EventRequest er ON e.id = er.event.id WHERE e.owner.id = :ownerId AND er.pending = false AND e.startDateTime BETWEEN :startDate AND :endDate")
    List<Event> findEventsByOwnerIdAndStartDateBetweenAndPendingIsFalse(
            @Param("ownerId") UUID ownerId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT er.event FROM EventRequest er WHERE er.user.id = :userId AND er.pending = false")
    List<Event> findByUserId(@Param("userId") UUID userId);

    @Query("SELECT er.event FROM EventRequest er WHERE er.user.id = :userId AND er.pending = false")
    List<Event> findByUserIdAndPendingFalse(@Param("userId") UUID userId);

    @Query("SELECT er.event FROM EventRequest er WHERE er.user.id = :userId AND er.pending = true")
    List<Event> findByUserIdAndPendingTrue(@Param("userId") UUID userId);

    @Query("SELECT er.event FROM EventRequest er WHERE er.user.id = :userId AND er.pending = true AND er.event.startDateTime BETWEEN :startDate AND :endDate")
    List<Event> findEventsByUserIdAndStartDateTimeBetweenAndPendingIsTrue(
            @Param("userId") UUID userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT er.event FROM EventRequest er WHERE er.user.id = :userId AND er.pending = false AND er.event.startDateTime BETWEEN :startDate AND :endDate")
    List<Event> findEventsByUserIdAndStartDateTimeBetweenAndPendingIsFalse(
            @Param("userId") UUID userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
