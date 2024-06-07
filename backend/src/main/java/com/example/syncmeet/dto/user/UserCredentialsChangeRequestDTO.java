package com.example.syncmeet.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialsChangeRequestDTO {

    private String firstName;

    private String lastName;
}
