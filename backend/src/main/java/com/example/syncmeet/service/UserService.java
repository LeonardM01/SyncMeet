package com.example.syncmeet.service;

import com.example.syncmeet.dto.UserDTO;
import com.example.syncmeet.model.User;

public interface UserService {
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO user, Long id);

    void deleteUser(Long id);
}
