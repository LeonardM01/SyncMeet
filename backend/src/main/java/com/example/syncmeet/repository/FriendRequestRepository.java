package com.example.syncmeet.repository;

import com.example.syncmeet.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    @Query("SELECT CASE WHEN fr.user.id = :userId THEN fr.friend ELSE fr.user END FROM FriendRequest fr WHERE fr.user.id = :userId OR fr.friend.id = :userId")
    List<FriendRequest> findByUserIdAndPendingRequestFalse(@Param("userId") Long userId);

    Optional<FriendRequest> findByUserIdAndFriendId(Long userId, Long friendId);
}
