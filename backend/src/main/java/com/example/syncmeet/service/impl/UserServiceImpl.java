package com.example.syncmeet.service.impl;

import com.example.syncmeet.dto.FriendRequestDTO;
import com.example.syncmeet.dto.UserDTO;
import com.example.syncmeet.error.exception.EntityNotFoundException;
import com.example.syncmeet.model.FriendRequest;
import com.example.syncmeet.model.User;
import com.example.syncmeet.repository.FriendRequestRepository;
import com.example.syncmeet.repository.UserRepository;
import com.example.syncmeet.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FriendRequestRepository friendRequestRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
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
    public FriendRequestDTO getFriendRequestByUserIdAndFriendId(Long userId, Long friendId) {
        return friendRequestRepository.findByUserIdAndFriendId(userId, friendId)
                .map(this::friendRequestToDTO).orElseThrow(() ->
                        new EntityNotFoundException("Friend request not found"));
    }

    @Override
    public FriendRequestDTO createFriendRequest(Long userId, Long friendId) {
        UserDTO user = getUserById(userId);
        UserDTO friend = getUserById(friendId);

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setUser(userDTOToEntity(user));
        friendRequest.setFriend(userDTOToEntity(friend));
        friendRequest.setPendingRequest(true);

        return friendRequestToDTO(friendRequestRepository.save(friendRequest));
    }

    @Override
    public void acceptFriendRequest(Long userId, Long friendId) {
        FriendRequestDTO friendRequest = getFriendRequestByUserIdAndFriendId(userId, friendId);
        friendRequest.setPendingRequest(false);
        friendRequestRepository.save(friendRequestDTOToEntity(friendRequest));
    }

    @Override
    public void rejectFriendRequest(Long userId, Long friendId) {
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
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::userToDTO).orElseThrow(() ->
                new EntityNotFoundException("User not found"));
    }

    @Override
    public List<UserDTO> getAllFriends(Long id) {
        List<FriendRequestDTO> friendRequests = friendRequestRepository.findByUserIdAndPendingRequestFalse(id)
                .stream().map(this::friendRequestToDTO).toList();
        return friendRequests.stream().map(FriendRequestDTO::getFriend).collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        return userToDTO(userRepository.save(userDTOToEntity(user)));
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
