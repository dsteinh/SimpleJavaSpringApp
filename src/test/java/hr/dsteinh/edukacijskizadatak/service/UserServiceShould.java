package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.User;
import hr.dsteinh.edukacijskizadatak.mother.UserMother;
import hr.dsteinh.edukacijskizadatak.repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserServiceShould {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepo userRepo;

    @Test
    void findAllUsers() {
        //given
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(UserMother.createUser());

        given(userRepo.findAll()).willReturn(expectedUsers);

        // When
        List<User> actualUsers = userService.findAll();

        // Then
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void saveUser() {
        User expectedUser = UserMother.createUser();

        given(userRepo.save(expectedUser)).willReturn(expectedUser);

        // When
        User actualUser = userService.save(expectedUser);

        // Then
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findByUserId() {
        long id = 1L;
        User expectedUser = UserMother.createUser();

        given(userRepo.findById(id)).willReturn(Optional.of(expectedUser));

        // When
        User actualUser = userService.findById(id).get();

        // Then
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void deleteByUserId() {
        //given
        long id = 1L;

        // When
        userService.deleteById(id);

        // Then
        verify(userRepo, times(1)).deleteById(id);
    }
}