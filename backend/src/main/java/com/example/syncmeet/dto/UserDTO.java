package com.example.syncmeet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    @NotNull(message = "Username is required")
    @Size(min = 3, message = "Username is too short")
    @Size(max = 255, message = "Username is too long")
    private String username;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email is invalid")
    private String email;

    private String profileImageUrl;
}
