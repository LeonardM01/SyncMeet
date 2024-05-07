package com.example.syncmeet.controller;

import com.example.syncmeet.dto.*;

import com.example.syncmeet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST controller for managing {@link com.example.syncmeet.model.User} and {@link com.example.syncmeet.model.FriendRequest}
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@code GET /users} : Fetch all users
     *
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with a list of all users as {@link UserDTO}s
     * in the body
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * {@code GET /users/email/{email}} : Fetch user by email
     *
     * @param email Email of wanted user
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the user as {@link UserDTO},
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/users/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    /**
     * {@code GET /users/{id}} : Fetch all friends of a user
     *
     * @param id ID of the specified user
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with all friends of the user as {@link UserDTO},
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/users/friends/{id}")
    public ResponseEntity<List<UserDTO>> getFriends(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getAllFriends(id));
    }

    /**
     * {@code GET /users/{id}} : Fetch user by ID
     *
     * @param id ID of wanted user
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the user as {@link UserDTO},
     * or with status {@code 404 (Not Found)} if the user does not exist
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * {@code GET /users/events/{eventId}} : Fetch all users of an event
     *
     * @param eventId ID of the event
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with all users of the event as {@link UserDTO}s
     * in the body
     */
    @GetMapping("/users/events/{eventId}")
    public ResponseEntity<List<UserDTO>> getUsersByEventId(@PathVariable UUID eventId) {
        return ResponseEntity.ok(userService.getUsersByEventId(eventId));
    }

    /**
     * {@code POST /api/users/{userId}/friends/{friendId}} : Send friend request
     *
     * @param userId ID of user that sends the friend request
     * @param friendId ID of user to whom the friend request is being sent
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and with the friend request as {@link FriendRequestDTO}
     * in the body, or with status {@code 404 (Not Found)} if either user does not exist, or with status {@code 400 (Bad request)}
     * if friend request already exists
     */
    @PostMapping("/users/{userId}/friends/{friendId}")
    public ResponseEntity<FriendRequestDTO> sendFriendRequest(
            @PathVariable UUID userId,
            @PathVariable UUID friendId
    ) {
        return ResponseEntity.ok(userService.createFriendRequest(userId, friendId));
    }

    /**
     * {@code POST /users} : Sign up
     *
     * @param userSignUpRequest Sign up request with necessary credentials as {@link UserSignUpRequestDTO}
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and a confirmation message in the body,
     * or with status {@code 400 (Bad Request)} if the request is invalid
     */
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> signUp(@Valid @RequestBody UserSignUpRequestDTO userSignUpRequest) {
        UserDTO user = new UserDTO(
                null,
                userSignUpRequest.getUsername(),
                userSignUpRequest.getEmail(),
                userSignUpRequest.getProfileImageUrl(),
                userSignUpRequest.getTier()
                );
        userService.createUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Signed up successfully");
        return ResponseEntity.ok(response);
    }

    /**
     * {@code PUT /users/username/{id}} : Change username
     *
     * @param id ID of specified user
     * @param usernameChangeRequest Request with new username as {@link UsernameChangeRequestDTO}
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and updated user as {@link UserDTO} in the body,
     * or with status {@code 404 (Not found)} if the user does not exist,
     * or with status {@code 400 (Bad request)} if the request is invalid
     */
    @PutMapping("/users/username/{id}")
    public ResponseEntity<UserDTO> changeUsername(
            @PathVariable UUID id,
            @Valid @RequestBody UsernameChangeRequestDTO usernameChangeRequest
            ) {
        UserDTO user = userService.getUserById(id);
        user.setUsername(usernameChangeRequest.getUsername());
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    /**
     * {@code PUT /users/{userId}/friend/{friendId}} : Accept friend request
     *
     * @param userId ID of the user that sent the friend request
     * @param friendId ID of the user to whom the friend request was sent to
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and confirmation message in the body,
     * or with status {@code 404 (Not found)} if the friend request does not exist
     */
    @PutMapping("/users/{userId}/friends/{friendId}")
    public ResponseEntity<Map<String, Object>> acceptFriendRequest(
            @PathVariable UUID userId,
            @PathVariable UUID friendId
    ) {
        userService.acceptFriendRequest(userId, friendId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Friend request accepted");
        return ResponseEntity.ok(response);
    }

    /**
     * {@code PUT /users/profileImage/{id}} : Change profile image
     *
     * @param id ID of the specified user
     * @param profileImageURLChangeRequest Request with the new profile image URL as {@link ProfileImageURLChangeRequestDTO}
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and updated user as {@link UserDTO} in the body,
     * or with status {@code 404 (Not found)} if the user does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/users/profileImage/{id}")
    public ResponseEntity<UserDTO> changeProfileImage(
            @PathVariable UUID id,
            @Valid @RequestBody ProfileImageURLChangeRequestDTO profileImageURLChangeRequest
            ) {
        UserDTO user = userService.getUserById(id);
        user.setProfileImageUrl(profileImageURLChangeRequest.getProfileImageUrl());
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    /**
     * {@code PUT /users/tier/{id}} : Change subscription tier
     *
     * @param id ID of the specified user
     * @param userTierUpdateRequest Request with the new tier type as {@link UserTierUpdateRequestDTO}
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and updated user as {@link UserDTO} in the body,
     * or with status {@code 404 (Not found)} if the user does not exist,
     * or with status {@code 400 (Bad request} if the request is invalid
     */
    @PutMapping("/users/tier/{id}")
    public ResponseEntity<UserDTO> changeTier(
            @PathVariable UUID id,
            @Valid @RequestBody UserTierUpdateRequestDTO userTierUpdateRequest
            ) {
        UserDTO user = userService.getUserById(id);
        user.setTier(userTierUpdateRequest.getTier());
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    /**
     * {@code DELETE /users/{userId}/friends/{friendId}} : Reject friend request OR Remove friend
     *
     * @param userId ID of the user that sent the friend request
     * @param friendId ID of the user to whom the friend request was sent to
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and confirmation message in the body,
     * or with status {@code 404 (Not found)} if either friend request does not exist or users are not friends
     */
    @DeleteMapping("/users/{userId}/friends/{friendId}")
    public ResponseEntity<Map<String, Object>> rejectFriendRequest(
            @PathVariable UUID userId,
            @PathVariable UUID friendId
    ) {
        FriendRequestDTO friendRequest = userService.getFriendRequestByUserIdAndFriendId(userId, friendId);

        Map<String, Object> response = new HashMap<>();
        // If the request is pending, then it is rejected
        if (friendRequest.isPendingRequest()) response.put("message", "Friend request rejected");
        // If it was accepted before, then the friend is removed
        else response.put("message", "Friend removed");

        userService.rejectFriendRequest(userId, friendId);

        return ResponseEntity.ok(response);
    }

    /**
     * {@code DELETE /users/{id}} : Remove user
     *
     * @param id ID of the user to be removed
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and confirmation message in the body,
     * or with status {@code 404 (Not found)} if user does not exist
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> removeUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }
}
