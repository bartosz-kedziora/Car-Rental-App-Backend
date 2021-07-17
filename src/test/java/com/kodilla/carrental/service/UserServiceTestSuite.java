package com.kodilla.carrental.service;

import com.kodilla.carrental.domain.User;
import com.kodilla.carrental.exception.UserNotFoundException;
import com.kodilla.carrental.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTestSuite {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User testUser() {
        return new User(
                1L,
                "Jim",
                "beam",
                "email",
                "password",
                111222);
    }

    @Test
    public void getUserByIdTest() throws UserNotFoundException {
        //Given
        User user = testUser();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //When
        User result = userService.getUserById(1L);

        //Then
        assertEquals(user.getId(), result.getId());
    }

    @Test
    public void getUserByEmailTest() throws UserNotFoundException {
        //Given
        User user = testUser();
        when(userRepository.findByEmail("email")).thenReturn(Optional.of(user));

        //When
        User result = userService.getUserByEmail("email");

        //Then
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    public void getUserByPhoneNumberTest() throws UserNotFoundException {
        //Given
        User user = testUser();
        when(userRepository.findByPhoneNumber(111222)).thenReturn(Optional.of(user));

        //When
        User result = userService.getUserByPhoneNumber(111222);

        //Then
        assertEquals(user.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    public void getAllUsersTest() {
        //Given
        User user = testUser();
        List<User> userList = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(userList);

        //When
        List<User> resultList = userService.getAllUsers();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
    }

    @Test
    public void saveUserTest() {
        //Given
        User user = testUser();
        when(userRepository.save(user)).thenReturn(user);

        //When
        User result = userService.saveUser(user);

        //Then
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    public void deleteUserTest() {
        //Given
        //When
        userService.deleteUser(2L);

        //Then
        verify(userRepository, times(1)).deleteById(2L);
    }
}
