package com.example.syncmeet.repository;

import com.example.syncmeet.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for {@link FriendRequest}
 */
@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, UUID> {
    @Query("SELECT CASE WHEN fr.user.id = :userId THEN fr.friend ELSE fr.user END FROM FriendRequest fr WHERE fr.user.id = :userId OR fr.friend.id = :userId AND fr.pendingRequest = false")
    List<FriendRequest> findByUserIdAndPendingRequestFalse(@Param("userId") UUID userId);

    @Query("SELECT fr FROM FriendRequest fr WHERE (:userId = fr.user.id AND :friendId = fr.friend.id) OR (:userId = fr.friend.id AND :friendId = fr.user.id)")
    Optional<FriendRequest> findByUserIdAndFriendId(@Param("userId") UUID userId, @Param("friendId") UUID friendId);
}
