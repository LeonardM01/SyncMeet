package com.example.syncmeet.controller;

import com.example.syncmeet.dto.friendRequest.FriendRequestDTO;
import com.example.syncmeet.dto.user.*;
import com.example.syncmeet.error.ErrorResponse;
import com.example.syncmeet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * REST controller for managing {@link com.example.syncmeet.model.User} and {@link com.example.syncmeet.model.FriendRequest}
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "https://syncmeet.space" } , allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
    @Operation(summary = "Fetch all users", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of users",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {UserDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
    @Operation(summary = "Fetch user by email", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {UserDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
    @Operation(summary = "Fetch all friends of a user", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of friends",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {UserDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
    @Operation(summary = "Fetch user by ID", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {UserDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
    @Operation(summary = "Fetch all users of an event", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of users",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {UserDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
    @Operation(summary = "Send friend request", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Friend request sent",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {FriendRequestDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Friend request already exists",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and created user as {@link UserDTO} in the body,
     * or with status {@code 400 (Bad Request)} if the request is invalid
     */
    @Operation(summary = "Sign up", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {UserDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/users")
    public ResponseEntity<UserDTO> signUp(
            @Valid @RequestBody UserSignUpRequestDTO userSignUpRequest,
            @RequestBody MultipartFile image
            ) {
        UserDTO user = new UserDTO(
                null,
                userSignUpRequest.getUsername(),
                userSignUpRequest.getEmail(),
                userSignUpRequest.getProfileImageUrl(),
                userSignUpRequest.getTier(),
                userSignUpRequest.getFirstName(),
                userSignUpRequest.getLastName()
                );

        return ResponseEntity.ok(userService.createUser(user, image));
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
    @Operation(summary = "Change username", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Username changed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {UserDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
     * {@code PUT /users/credential/{id}} : Change first and last name
     *
     * @param id ID of the specified user
     * @param userCredentials Request with new first and last name as {@link UserCredentialsChangeRequestDTO}
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and updated user as {@link UserDTO} in the body,
     * or with status {@code 404 (Not found)} if the user does not exist,
     * or with status {@code 400 (Bad request)} if the request is invalid
     */
    @Operation(summary = "Change first and last name", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "First and last name changed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {UserDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/users/credential/{id}")
    public ResponseEntity<UserDTO> changeUserCredentials(
            @PathVariable UUID id,
            @Valid @RequestBody UserCredentialsChangeRequestDTO userCredentials
    ) {
        UserDTO user = userService.getUserById(id);
        user.setFirstName(userCredentials.getFirstName());
        user.setLastName(userCredentials.getLastName());
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
    @Operation(summary = "Accept friend request", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Friend request accepted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {Map.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Friend request not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
     * @param image Image file to be uploaded
     * @return {@link ResponseEntity} with status {@code 200 (Ok)} and updated user as {@link UserDTO} in the body,
     * or with status {@code 400 (Bad request)} if the provided file is not an image,
     * or with status {@code 404 (Not found)} if the user does not exist
     */
    @Operation(summary = "Change profile image", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile image changed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {UserDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/users/profileImage/{id}")
    public ResponseEntity<UserDTO> changeProfileImage(
            @PathVariable UUID id,
            @RequestBody MultipartFile image
            ) {
        return ResponseEntity.ok(userService.changeProfileImageUrl(image, id));
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
    @Operation(summary = "Change subscription tier", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tier changed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {UserDTO.class, ErrorResponse.class})
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
    @Operation(summary = "Reject friend request OR Remove friend", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Friend request rejected OR Friend removed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Friend request not found OR Users are not friends",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
    @Operation(summary = "Remove user", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> removeUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }
}
