package com.example.syncmeet.repository;

import com.example.syncmeet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for {@link User}
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("SELECT er.user FROM EventRequest er WHERE er.event.id = :eventId")
    List<User> findByEventId(@Param("eventId") UUID eventId);

    @Query("SELECT er.user FROM EventRequest er WHERE er.event.id = :eventId AND er.pending = false")
    List<User> findByEventIdAndPendingFalse(@Param("eventId") UUID eventId);
}
