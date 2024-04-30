package com.example.syncmeet.service;

import com.example.syncmeet.dto.FriendRequestDTO;
import com.example.syncmeet.dto.UserDTO;
import com.example.syncmeet.model.FriendRequest;
import com.example.syncmeet.model.User;
import com.example.syncmeet.model.User.TierType;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO userToDTO(User user);

    User userDTOToEntity(UserDTO userDTO);

    FriendRequestDTO friendRequestToDTO(FriendRequest friendRequest);

    FriendRequest friendRequestDTOToEntity(FriendRequestDTO friendRequestDTO);

    FriendRequestDTO getFriendRequestByUserIdAndFriendId(UUID userId, UUID friendId);

    FriendRequestDTO createFriendRequest(UUID userId, UUID friendId);

    void acceptFriendRequest(UUID userId, UUID friendId);

    void rejectFriendRequest(UUID userId, UUID friendId);

    List<UserDTO> getAllUsers();

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(UUID id);

    List<UserDTO> getAllFriends(UUID id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO user, UUID id);

    void updateTier(UUID id, TierType tier);

    void deleteUser(UUID id);
}
