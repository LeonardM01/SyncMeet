package com.example.syncmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestDTO {
    private UUID id;

    private UserDTO user;

    private UserDTO friend;

    private boolean pendingRequest;
}
