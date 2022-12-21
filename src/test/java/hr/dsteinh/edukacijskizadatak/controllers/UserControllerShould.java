package hr.dsteinh.edukacijskizadatak.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.User;
import hr.dsteinh.edukacijskizadatak.mother.UserMother;
import hr.dsteinh.edukacijskizadatak.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerShould {

    public static final String TEST_USER_JSON = "src/test/resources/controller/test_user.json";
    private static final String API_USERS = "/api/users";
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    void findAllUsers() throws Exception {

        User user = UserMother.createUser();
        List<User> users = new ArrayList<>();

        users.add(user);

        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_USERS)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findUserById() throws Exception {
        User user = UserMother.createUser();
        user.setId(1L);

        when(userService.findById(1L)).thenReturn(Optional.of(user));

        mapper.writeValue(new File(TEST_USER_JSON), user);
        String jsonUserToString = mapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_USERS + "/1"))
                .andExpect(content().string(jsonUserToString))
                .andExpect(status().isOk());
    }

    @Test
    void saveUser() throws Exception {
        User user =  UserMother.createUser();

        when(userService.save(user)).thenReturn(user);

        mapper.writeValue(new File(TEST_USER_JSON), user);
        String jsonUserToString = mapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(API_USERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUserToString))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserById() throws Exception {
        mockMvc.perform(delete(API_USERS + "/2"))
                .andExpect(status().isOk());
    }
}