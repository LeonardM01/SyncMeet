package com.example.syncmeet.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing a profile image change
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImageURLChangeRequestDTO {
    private String profileImageUrl;
}