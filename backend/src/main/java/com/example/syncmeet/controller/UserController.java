package com.example.syncmeet.controller;

import com.example.syncmeet.dto.UserDTO;
import com.example.syncmeet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/user/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
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
            @PathVariable Long id,
            @RequestBody String username
    ) {
        UserDTO user = userService.getUserById(id);
        user.setUsername(username);
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @PutMapping("/api/users/profileImage/{id}")
    public ResponseEntity<UserDTO> changeProfileImage(
            @PathVariable Long id,
            @RequestBody String profileImageUrl
    ) {
        UserDTO user = userService.getUserById(id);
        user.setProfileImageUrl(profileImageUrl);
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Map<String, Object>> removeUser(@PathVariable Long id) {
        userService.deleteUser(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }
}
