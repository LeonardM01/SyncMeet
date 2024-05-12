package com.example.syncmeet.dto.user;

import com.example.syncmeet.model.User.TierType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for the {@link com.example.syncmeet.model.User} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    private String username;

    private String email;

    private String profileImageUrl;

    private TierType tier;
}
