package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.User;
import com.finalproject.engineerapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    private final User user1 = new User(1L, "Tom", "rhjdd56h5", true);
    private final User user2 = new User(2L, "Jim", "vhun83#$", true);
    private final User user3 = new User(3L, "Fin", "YG%6%^5^", false);
    private final List<User> users = new ArrayList<>();

    @Test
    void getUsersTest() {
        users.add(user1);
        users.add(user2);
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(2, userService.getUsers().size());
    }

    @Test
    void updateUserTest() {
        userService.updateUser(user1);
        verify(userRepository,times(1)).save(user1);
    }

    @Test
    void addUserTest() {
        userService.addUser(user3);
        verify(userRepository, times(1)).save(user3);
    }

    @Test
    void deleteUserTest() {
        userRepository.deleteUser(user3);
        verify(userRepository, times(1)).delete(user3);
    }

    @Test
    void findUserByIdTest() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(user1));
        assertEquals("Tom", userService.findUserById(id).get().getUsername());
    }
}