package com.example.syncmeet.service;

import com.example.syncmeet.dto.FriendRequestDTO;
import com.example.syncmeet.dto.UserDTO;
import com.example.syncmeet.model.FriendRequest;
import com.example.syncmeet.model.User;

import java.util.List;
import java.util.UUID;

/**
 * Service Interface for managing {@link User} and {@link FriendRequest}
 */
public interface UserService {
    /**
     * Convert {@link User} to {@link UserDTO}
     *
     * @param user the entity to convert to DTO
     * @return {@link UserDTO} created from user
     */
    UserDTO userToDTO(User user);

    /**
     * Convert {@link UserDTO} to {@link User}
     *
     * @param userDTO the DTO to convert to entity
     * @return {@link User} created from userDTO
     */
    User userDTOToEntity(UserDTO userDTO);

    /**
     * Covert {@link FriendRequest} to {@link FriendRequestDTO}
     *
     * @param friendRequest the entity to convert to DTO
     * @return {@link FriendRequestDTO} created from friendRequest
     */
    FriendRequestDTO friendRequestToDTO(FriendRequest friendRequest);

    /**
     *
     * @param friendRequestDTO the DTO to convert to entity
     * @return {@link FriendRequest} created from friendRequestDTO
     */
    FriendRequest friendRequestDTOToEntity(FriendRequestDTO friendRequestDTO);

    /**
     * Get friend request by user ID and friend ID
     *
     * @param userId ID of the user that sent the friend request
     * @param friendId ID of the user to whom the friend request was sent to
     * @return the {@link FriendRequestDTO} with specified userId and friendId
     */
    FriendRequestDTO getFriendRequestByUserIdAndFriendId(UUID userId, UUID friendId);

    /**
     * Create friend request that the {@link User} with userId sent to the user with friendId
     *
     * @param userId ID of the user that sent the friend request
     * @param friendId ID of the user to whom the friend request was sent to
     * @return the {@link FriendRequestDTO} with specified userId and friendId
     */
    FriendRequestDTO createFriendRequest(UUID userId, UUID friendId);

    /**
     * Accept friend request
     *
     * @param userId ID of the user that sent the friend request
     * @param friendId ID of the user to whom the friend request was sent to
     */
    void acceptFriendRequest(UUID userId, UUID friendId);

    /**
     * Reject friend request OR Remove friend
     *
     * @param userId ID of the user that sent the friend request
     * @param friendId ID of the user to whom the friend request was sent to
     */
    void rejectFriendRequest(UUID userId, UUID friendId);

    /**
     *
     * @return The list of all users as {@link UserDTO}s
     */
    List<UserDTO> getAllUsers();

    /**
     * Get user by email
     *
     * @param email Email of the wanted user
     * @return The {@link UserDTO} with the specified email
     */
    UserDTO getUserByEmail(String email);

    /**
     * Get user by ID
     *
     * @param id ID of the wanted user
     * @return The {@link UserDTO} with the specified ID
     */
    UserDTO getUserById(UUID id);

    /**
     * Get all friends of the user with specified ID
     *
     * @param id ID of the wanted user
     * @return List of all friends of user with specified ID as {@link UserDTO}
     */
    List<UserDTO> getAllFriends(UUID id);

    /**
     * Create user
     *
     * @param userDTO The entity to create
     * @return The persisted entity as {@link UserDTO}
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     * Update user
     *
     * @param user The entity to update
     * @param id ID of user to update
     * @return The persisted entity as {@link UserDTO}
     */
    UserDTO updateUser(UserDTO user, UUID id);

    /**
     * Delete user
     *
     * @param id ID of the user to delete
     */
    void deleteUser(UUID id);
}
