package com.example.syncmeet.controller;

import com.example.syncmeet.dto.ProfileImageURLChangeRequestDTO;
import com.example.syncmeet.dto.FriendRequestDTO;

import com.example.syncmeet.dto.UserDTO;
import com.example.syncmeet.dto.UsernameChangeRequestDTO;
import com.example.syncmeet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/api/users/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/api/users/friends/{id}")
    public ResponseEntity<List<UserDTO>> getFriends(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getAllFriends(id));
    }

    @PostMapping("/api/users/{userId}/friends/{friendId}")
    public ResponseEntity<FriendRequestDTO> sendFriendRequest(
            @PathVariable UUID userId,
            @PathVariable UUID friendId
    ) {
        return ResponseEntity.ok(userService.createFriendRequest(userId, friendId));
    }

    @PostMapping("/api/users")
    public ResponseEntity<Map<String, Object>> signUp(@Valid @RequestBody UserDTO user) {
        userService.createUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Signed up successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/users/username/{id}")
    public ResponseEntity<UserDTO> changeUsername(
            @PathVariable UUID id,
            @Valid @RequestBody UsernameChangeRequestDTO usernameChangeRequest
            ) {
        UserDTO user = userService.getUserById(id);
        user.setUsername(usernameChangeRequest.getUsername());
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @PutMapping("/api/users/{userId}/friends/{friendId}")
    public ResponseEntity<Map<String, Object>> acceptFriendRequest(
            @PathVariable UUID userId,
            @PathVariable UUID friendId
    ) {
        userService.acceptFriendRequest(userId, friendId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Friend request accepted");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/users/profileImage/{id}")
    public ResponseEntity<UserDTO> changeProfileImage(
            @PathVariable UUID id,
            @Valid @RequestBody ProfileImageURLChangeRequestDTO profileImageURLChangeRequest
            ) {
        UserDTO user = userService.getUserById(id);
        user.setProfileImageUrl(profileImageURLChangeRequest.getProfileImageUrl());
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @DeleteMapping("/api/users/{userId}/friends/{friendId}")
    public ResponseEntity<Map<String, Object>> rejectFriendRequest(
            @PathVariable UUID userId,
            @PathVariable UUID friendId
    ) {
        FriendRequestDTO friendRequest = userService.getFriendRequestByUserIdAndFriendId(userId, friendId);

        Map<String, Object> response = new HashMap<>();
        if (friendRequest.isPendingRequest()) response.put("message", "Friend request rejected");
        else response.put("message", "Friend removed");

        userService.rejectFriendRequest(userId, friendId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Map<String, Object>> removeUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }
}
