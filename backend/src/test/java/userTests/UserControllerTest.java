package userTests;//package userTests;
//
//import com.example.syncmeet.SyncMeetApplication;
//import com.example.syncmeet.controller.UserController;
//import com.example.syncmeet.dto.user.UserDTO;
//import com.example.syncmeet.dto.user.UserSignUpRequestDTO;
//import com.example.syncmeet.model.User;
//import com.example.syncmeet.service.impl.UserServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.CoreMatchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@ContextConfiguration(classes = UserController.class)
//@WebMvcTest(UserController.class)
//@ExtendWith(MockitoExtension.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserServiceImpl userService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    User user;
//    UserDTO userDTO1;
//
//    @BeforeEach
//    public void init() {
//        user = new User();
//        userDTO1 = new UserDTO();
//    }
//
//    @Test
//    public void testSignUpSuccess() throws Exception{
//
//        UserSignUpRequestDTO userSignUpRequestDTO = new UserSignUpRequestDTO(
//                "username", "user@email.com", "imageUrl", User.TierType.free
//        );
//
//        given(userService.createUser(ArgumentMatchers.any())).willAnswer(invocation -> {
//            UserDTO userDTO = invocation.getArgument(0);
//            User user = new User();
//            user.setUsername(userDTO.getUsername());
//            user.setEmail(userDTO.getEmail());
//            user.setTier(userDTO.getTier());
//            return user;
//        });
//
//        ResultActions response = mockMvc.perform(post("/api/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userSignUpRequestDTO)));
//
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(userSignUpRequestDTO.getUsername())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userSignUpRequestDTO.getEmail())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.tier", CoreMatchers.is(userSignUpRequestDTO.getTier().name())));
//    }
//}

import com.example.syncmeet.controller.UserController;
import com.example.syncmeet.dto.user.UserDTO;
import com.example.syncmeet.dto.user.UserSignUpRequestDTO;
import com.example.syncmeet.model.User;
import com.example.syncmeet.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @MockBean
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
    }

    @Test
    public void testSignUp() {
        // Create a sample user sign up request
        UserSignUpRequestDTO userSignUpRequest = new UserSignUpRequestDTO(
                "username",
                "email@example.com",
                "profileImageUrl",
                User.TierType.free);

        // Create a sample user DTO
        UserDTO userDTO = new UserDTO(null, userSignUpRequest.getUsername(), userSignUpRequest.getEmail(), userSignUpRequest.getProfileImageUrl(), userSignUpRequest.getTier());

        // Mock the createUser method to return the user DTO
        when(userService.createUser(userDTO)).thenReturn(userDTO);

        // Call the signUp method
        ResponseEntity<Map<String, Object>> response = userController.signUp(userSignUpRequest);

        // Verify that the createUser method was called
        verify(userService).createUser(userDTO);

        // Assert that the response is OK and contains the expected message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Signed up successfully", response.getBody().get("message"));
    }

    @Test
    public void testGetUserByEmail() throws Exception {

        // Create a sample user
        UserDTO userDTO = new UserDTO(UUID.randomUUID(),"username", "user@email.com", "imageUrl", User.TierType.free);

        // Mock the getUserByEmail method to return the user DTO
        given(userService.getUserByEmail("user@email.com")).willReturn(userDTO);

        // Perform the GET request
        ResultActions response = mockMvc.perform(get("/api/users/email/{email}", "user@email.com"));

        // Verify that the response is OK and contains the expected user data
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.username", CoreMatchers.is(userDTO.getUsername())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(userDTO.getEmail())))
                .andExpect(jsonPath("$.tier", CoreMatchers.is(userDTO.getTier().name())));
    }
}