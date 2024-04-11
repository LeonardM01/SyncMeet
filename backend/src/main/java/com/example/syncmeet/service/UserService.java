package com.example.syncmeet.service;

import com.example.syncmeet.dto.FriendRequestDTO;
import com.example.syncmeet.dto.UserDTO;
import com.example.syncmeet.model.FriendRequest;
import com.example.syncmeet.model.User;

import java.util.List;

public interface UserService {
    UserDTO userToDTO(User user);

    User userDTOToEntity(UserDTO userDTO);

    FriendRequestDTO friendRequestToDTO(FriendRequest friendRequest);

    FriendRequest friendRequestDTOToEntity(FriendRequestDTO friendRequestDTO);

    FriendRequestDTO getFriendRequestByUserIdAndFriendId(Long userId, Long friendId);

    FriendRequestDTO createFriendRequest(Long userId, Long friendId);

    void acceptFriendRequest(Long userId, Long friendId);

    void rejectFriendRequest(Long userId, Long friendId);

    List<UserDTO> getAllUsers();

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllFriends(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO user, Long id);

    void deleteUser(Long id);
}
