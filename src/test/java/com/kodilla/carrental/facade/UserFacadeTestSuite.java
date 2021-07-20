package com.kodilla.carrental.facade;

import com.kodilla.carrental.domain.User;
import com.kodilla.carrental.dto.UserDto;
import com.kodilla.carrental.exception.UserNotFoundException;
import com.kodilla.carrental.mapper.UserMapper;
import com.kodilla.carrental.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserFacadeTestSuite {

    @InjectMocks
    private UserFacade userFacade;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserService userService;

    private User testUser() {
        return new User(
                1L,
                "Jim",
                "Beam",
                "email",
                "password",
                111222);
    }

    private UserDto testUserDto() {
        return new UserDto(
                1L,
                "Jim",
                "Beam",
                "email",
                "password",
                111222,
                LocalDate.now());
    }

    @Test
    public void getUserByIdTest() throws UserNotFoundException {
        //Given
        User user = testUser();
        UserDto userDto = testUserDto();

        when(userService.getUserById(1L)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto result = userFacade.getUserById(1L);

        //Then
        assertEquals(userDto.getId(), result.getId());
    }

    @Test
    public void getUserByEmailTest() throws UserNotFoundException {
        //Given
        User user = testUser();
        UserDto userDto = testUserDto();

        when(userService.getUserByEmail("email")).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto result = userFacade.getUserByEmail("email");

        //Then
        assertEquals(userDto.getEmail(), result.getEmail());
    }

    @Test
    public void getUserByPhoneNumberTest() throws UserNotFoundException {
        //Given
        User user = testUser();
        UserDto userDto = testUserDto();

        when(userService.getUserByPhoneNumber(111222)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto result = userFacade.getUserByPhoneNumber(111222);

        //Then
        assertEquals(userDto.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    public void getUsersTest() {
        //Given
        User user =testUser();
        UserDto userDto = testUserDto();

        List<User> userList = Collections.singletonList(user);
        List<UserDto> userDtoList = Collections.singletonList(userDto);

        when(userService.getAllUsers()).thenReturn(userList);
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);

        //When
        List<UserDto> resultList = userFacade.getAllUsers();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(u -> {
            assertEquals(u.getId(), userDto.getId());
            assertEquals(u.getName(), userDto.getName());
            assertEquals(u.getPhoneNumber(), userDto.getPhoneNumber());
            assertEquals(u.getPassword(), userDto.getPassword());
        });
    }

    @Test
    public void userSaveTest() {
        //Given
        User user = testUser();
        UserDto userDto = testUserDto();

        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userService.saveUser(user)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto savedUser = userFacade.saveUser(userDto);

        //Then
        assertEquals(userDto.getId(), savedUser.getId());
        assertEquals(userDto.getName(), savedUser.getName());
        assertEquals(LocalDate.now(), savedUser.getAccountCreated());
    }

    @Test
    public void updateUserTest(){
        //Given
        User user = testUser();
        UserDto userDto = testUserDto();

        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userService.saveUser(user)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto modifiedUser = userFacade.updateUser(userDto);

        //Then
        assertEquals(userDto.getName(), modifiedUser.getName());
        assertEquals(userDto.getEmail(), modifiedUser.getEmail());
        assertEquals(userDto.getPhoneNumber(), modifiedUser.getPhoneNumber());
    }


    @Test
    public void deleteUserTest(){

        //When
        userFacade.deleteUser(2L);

        //Then
        verify(userService, times(1)).deleteUser(2L);
    }
}

