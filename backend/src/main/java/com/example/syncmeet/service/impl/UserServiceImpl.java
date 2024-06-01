package com.example.syncmeet.service.impl;

import com.example.syncmeet.dto.friendRequest.FriendRequestDTO;
import com.example.syncmeet.dto.user.UserDTO;
import com.example.syncmeet.error.exception.EntityNotFoundException;
import com.example.syncmeet.error.exception.IdMismatchException;

import com.example.syncmeet.error.exception.RequestException;
import com.example.syncmeet.model.FriendRequest;

import com.example.syncmeet.model.User;
import com.example.syncmeet.repository.EventRepository;
import com.example.syncmeet.repository.FriendRequestRepository;
import com.example.syncmeet.repository.UserRepository;
import com.example.syncmeet.service.S3Service;
import com.example.syncmeet.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.Objects;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link User} and {@link FriendRequest}
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final EventRepository eventRepository;
    private final S3Service s3Service;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FriendRequestRepository friendRequestRepository, EventRepository eventRepository, S3Service s3Service, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.eventRepository = eventRepository;
        this.s3Service = s3Service;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User userDTOToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public FriendRequestDTO friendRequestToDTO(FriendRequest friendRequest) {
        return modelMapper.map(friendRequest, FriendRequestDTO.class);
    }

    @Override
    public FriendRequest friendRequestDTOToEntity(FriendRequestDTO friendRequestDTO) {
        return modelMapper.map(friendRequestDTO, FriendRequest.class);
    }

    @Override
    public FriendRequestDTO getFriendRequestByUserIdAndFriendId(UUID userId, UUID friendId) {
        return friendRequestRepository.findByUserIdAndFriendId(userId, friendId)
                .map(this::friendRequestToDTO).orElseThrow(() ->
                        new EntityNotFoundException("Friend request not found"));
    }

    @Override
    public FriendRequestDTO createFriendRequest(UUID userId, UUID friendId) {
        if (userId.equals(friendId)) {
            throw new RequestException("Users can't be friends with themselves");
        }
        if (friendRequestRepository.findByUserIdAndFriendId(userId, friendId).isPresent()) {
            throw new RequestException("Friend request already exists");
        }

        UserDTO user = getUserById(userId);
        UserDTO friend = getUserById(friendId);

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setUser(userDTOToEntity(user));
        friendRequest.setFriend(userDTOToEntity(friend));
        friendRequest.setPendingRequest(true);

        return friendRequestToDTO(friendRequestRepository.save(friendRequest));
    }

    @Override
    public void acceptFriendRequest(UUID userId, UUID friendId) {
        FriendRequestDTO friendRequest = getFriendRequestByUserIdAndFriendId(userId, friendId);
        if (!friendRequest.isPendingRequest()) {
            throw new RequestException("Users are already friends");
        }
        friendRequest.setPendingRequest(false);
        friendRequestRepository.save(friendRequestDTOToEntity(friendRequest));
    }

    @Override
    public void rejectFriendRequest(UUID userId, UUID friendId) {
        FriendRequestDTO friendRequest = getFriendRequestByUserIdAndFriendId(userId, friendId);
        friendRequestRepository.delete(friendRequestDTOToEntity(friendRequest));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::userToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::userToDTO).orElseThrow(() ->
                new EntityNotFoundException("Couldn't find user with this email"));
    }

    @Override
    public UserDTO getUserById(UUID id) {
        return userRepository.findById(id).map(this::userToDTO).orElseThrow(() ->
                new EntityNotFoundException("User not found"));
    }

    @Override
    public List<UserDTO> getUsersByEventId(UUID eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new EntityNotFoundException("Event not found");
        }

        return userRepository.findByEventId(eventId).stream().map(this::userToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllFriends(UUID id) {
        List<FriendRequestDTO> friendRequests = friendRequestRepository.findByUserIdAndPendingRequestFalse(id)
                .stream().map(this::friendRequestToDTO).toList();
        return friendRequests.stream().map(FriendRequestDTO::getFriend).collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO user, MultipartFile image) {
        if (image.isEmpty() && user.getProfileImageUrl() == null) {
            user.setProfileImageUrl(null);
        }
        else if (!Objects.requireNonNull(image.getContentType()).startsWith("image/")) {
            throw new IllegalArgumentException("File must be an image");
        }
        else if (!image.isEmpty()) {
            String imageUrl = s3Service.uploadFile(image);
            user.setProfileImageUrl(imageUrl);
        }

        return userToDTO(userRepository.save(userDTOToEntity(user)));
    }

    @Override
    public UserDTO updateUser(UserDTO user, UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }

        if (!Objects.equals(user.getId(), id)) {
            throw new IdMismatchException("ID's don't match");
        }

        return userToDTO(userRepository.save(userDTOToEntity(user)));
    }

    @Override
    public UserDTO changeProfileImageUrl(MultipartFile image, UUID id) {
        UserDTO user = getUserById(id);

        if (image.isEmpty()) {
            user.setProfileImageUrl(null);

            return updateUser(user, id);
        }
        else if (!Objects.requireNonNull(image.getContentType()).startsWith("image/")) {
            throw new IllegalArgumentException("File must be an image");
        }

        String imageUrl = s3Service.uploadFile(image);

        if (user.getProfileImageUrl() != null && !user.getProfileImageUrl().contains("googleusercontent.com")) {
            s3Service.removeImage(user.getProfileImageUrl());
        }

        user.setProfileImageUrl(imageUrl);

        return updateUser(user, id);
    }

    @Override
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }
}
