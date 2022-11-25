package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.User;
import hr.dsteinh.edukacijskizadatak.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserEndpointControllerTest {

    @Mock
    UserService userService;

    UserEndpointController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        controller = new UserEndpointController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAllUsers() throws Exception {

        User user = new User();
        user.setId(1L);

        List<User> users = new ArrayList<>();
        users.add(user);

        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk());
    }

    @Test
    void findUserById() throws Exception {
        User user = new User();
        user.setId(1L);

        when(userService.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"firstName\": \"test\", \"lastName\": \"test\", \"oib\": \"test\" }"))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        User user = new User();
        user.setId(1L);

        when(userService.createOrUpdateUser(user)).thenReturn(any(User.class));

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"test\", \"lastName\": \"test\", \"oib\": \"test\" }"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserById() throws Exception {

        mockMvc.perform(delete("/api/user/2"))
                .andExpect(status().isOk());

    }
}