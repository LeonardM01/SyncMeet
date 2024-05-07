package com.example.syncmeet.repository;

import com.example.syncmeet.model.EventRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRequestRepository extends JpaRepository<EventRequest, UUID> {

    Optional<EventRequest> findByUserIdAndEventId(UUID userId, UUID eventId);
}
