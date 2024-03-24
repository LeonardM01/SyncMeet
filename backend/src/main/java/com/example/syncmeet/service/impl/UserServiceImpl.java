package com.example.syncmeet.service.impl;

import com.example.syncmeet.dto.UserDTO;
import com.example.syncmeet.error.exception.EntityNotFoundException;
import com.example.syncmeet.model.User;
import com.example.syncmeet.repository.UserRepository;
import com.example.syncmeet.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::toDTO).orElseThrow(() ->
                new EntityNotFoundException("Couldn't find user with this email"));
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::toDTO).orElseThrow(() ->
                new EntityNotFoundException("User not found"));
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        return toDTO(userRepository.save(toEntity(user)));
    }

    @Override
    public UserDTO updateUser(UserDTO user, Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }

        return createUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }
}
