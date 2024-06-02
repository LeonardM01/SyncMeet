package userTests;

import com.example.syncmeet.dto.friendRequest.FriendRequestDTO;
import com.example.syncmeet.dto.user.UserDTO;
import com.example.syncmeet.model.FriendRequest;
import com.example.syncmeet.model.User;
import com.example.syncmeet.repository.EventRepository;
import com.example.syncmeet.repository.FriendRequestRepository;
import com.example.syncmeet.repository.UserRepository;
import com.example.syncmeet.service.UserService;
import com.example.syncmeet.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FriendRequestRepository friendRequestRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ModelMapper modelMapper;

    private UserService userService;

    private User user1;
    private User user2;
    private UserDTO userDTO1;
    private UserDTO userDTO2;

    @BeforeEach
    public void setup() {
        userService = new UserServiceImpl(userRepository, friendRequestRepository, eventRepository, modelMapper);

        // Setting up the users...
        user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setUsername("username");
        user1.setEmail("user@email.com");
        user1.setProfileImageUrl("https://example.com/profile.jpg");
        user1.setTier(User.TierType.free);

        user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setUsername("username2");
        user2.setEmail("user2@email.com");
        user2.setProfileImageUrl("https://example.com/profile2.jpg");
        user2.setTier(User.TierType.free);

        // ...and their DTOs
        userDTO1 = new UserDTO();
        userDTO1.setId(user1.getId());
        userDTO1.setUsername(user1.getUsername());
        userDTO1.setEmail(user1.getEmail());
        userDTO1.setProfileImageUrl(user1.getProfileImageUrl());
        userDTO1.setTier(user1.getTier());

        userDTO2 = new UserDTO();
        userDTO2.setId(user2.getId());
        userDTO2.setUsername(user2.getUsername());
        userDTO2.setEmail(user2.getEmail());
        userDTO2.setProfileImageUrl(user2.getProfileImageUrl());
        userDTO2.setTier(user2.getTier());

        // Mocking saving user into repository
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Mocking modelMapper
        // DTO to User
        when(modelMapper.map(ArgumentMatchers.any(User.class), ArgumentMatchers.eq(UserDTO.class)))
                .thenAnswer(invocation -> {
                    User user = invocation.getArgument(0);
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setUsername(user.getUsername());
                    userDTO.setEmail(user.getEmail());
                    userDTO1.setProfileImageUrl(user.getProfileImageUrl());
                    userDTO1.setTier(user.getTier());
                    return userDTO;
                });

        // User to DTO
        when(modelMapper.map(ArgumentMatchers.any(UserDTO.class), ArgumentMatchers.eq(User.class)))
                .thenAnswer(invocation -> {
                    UserDTO userDTO = invocation.getArgument(0);
                    User user = new User();
                    user.setId(userDTO.getId());
                    user.setUsername(userDTO.getUsername());
                    user.setEmail(userDTO.getEmail());
                    user.setProfileImageUrl(userDTO.getProfileImageUrl());
                    user.setTier(userDTO.getTier());
                    return user;
                });
    }

    @Test
    public void testCreateUser() {

        UserDTO createdUser = userService.createUser(userDTO1);
        Assertions.assertNotNull(createdUser);
        Assertions.assertEquals(userDTO1.getUsername(), createdUser.getUsername());
    }

    @Test
    void testCreateUser_then_DeleteUser() {

        UserDTO createdUser = userService.createUser(userDTO1);
        User user1 = userService.userDTOToEntity(createdUser);

        assertNotNull(user1);
        assertNotNull(user1.getId());

        Mockito.doReturn(true)
                .when(userRepository).existsById(user1.getId());

        userService.deleteUser(user1.getId());

        verify(userRepository, times(1)).deleteById(user1.getId());

        Mockito.doReturn(false)
                .when(userRepository).existsById(user1.getId());

        assertFalse(userRepository.existsById(user1.getId()));
    }

//    @Test
//    void testAcceptFriendRequest() {
//
//        UserDTO createdUser1 = userService.createUser(userDTO1);
//        UserDTO createdUser2 = userService.createUser(userDTO2);
//
//        UUID id1 = createdUser1.getId();
//        UUID id2 = createdUser2.getId();
//
//        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
//        when(userRepository.findById(user2.getId())).thenReturn(Optional.of(user2));
//
//        Mockito.doReturn(Optional.of(FriendRequest.class))
//                .when(friendRequestRepository).findByUserIdAndFriendId(id1, id2);
//        Mockito.doReturn(Optional.of(FriendRequest.class))
//                .when(friendRequestRepository).findByUserIdAndFriendId(id2, id1);
//
//        userService.createFriendRequest(id1, id2);
//        userService.acceptFriendRequest(id2, id1);
//
//        // Verify that the users are friends
//        Mockito.verify(friendRequestRepository).save(Mockito.argThat(fr ->
//                fr.getUser().equals(createdUser1) && fr.getFriend().equals(createdUser2) && !fr.isPendingRequest()));
//        Mockito.verify(friendRequestRepository).save(Mockito.argThat(fr ->
//                fr.getUser().equals(createdUser2) && fr.getFriend().equals(createdUser2) && !fr.isPendingRequest()));
//    }

}