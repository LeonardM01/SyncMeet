package com.example.syncmeet.dto.user;

import com.example.syncmeet.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing a user sign up
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequestDTO {

    @NotNull(message = "Username is required")
    @Size(min = 3, message = "Username is too short")
    @Size(max = 255, message = "Username is too long")
    private String username;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email is invalid")
    private String email;

    @NotNull(message = "First name is required")
    @Size(max = 255, message = "First name is too long")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(max = 255, message = "Last name is too long")
    private String lastName;

    private String profileImageUrl;

    @NotNull(message = "Tier is required")
    private User.TierType tier;
}
