package com.kodilla.carrental.mapper;

import com.kodilla.carrental.domain.User;
import com.kodilla.carrental.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

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
    public void mapToUserTest() {
        //Given
        UserDto userDto = testUserDto();

        //When
        User mappedUser = userMapper.mapToUser(userDto);
        mappedUser.setAccountCreated(LocalDate.now());

        //Then
        assertEquals(1L, (long) mappedUser.getId());
        assertEquals("Jim", mappedUser.getName());
        assertEquals("Beam", mappedUser.getLastName());
        assertEquals("email", mappedUser.getEmail());
        assertEquals("password", mappedUser.getPassword());
        assertEquals(111222, mappedUser.getPhoneNumber());
        assertEquals(LocalDate.now(), mappedUser.getAccountCreated());
    }

    @Test
    public void mapToUserDtoTest() {
        //Given
        User user = testUser();
        user.setAccountCreated(LocalDate.now());

        //When
        UserDto mappedUserDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(1L, (long) mappedUserDto.getId());
        assertEquals("Jim", mappedUserDto.getName());
        assertEquals("Beam", mappedUserDto.getLastName());
        assertEquals("email", mappedUserDto.getEmail());
        assertEquals("password", mappedUserDto.getPassword());
        assertEquals(111222, mappedUserDto.getPhoneNumber());
        assertEquals(LocalDate.now(), mappedUserDto.getAccountCreated());
    }

    @Test
    public void mapToUserDtoListTest() {
        //Given
        User user = testUser();
        user.setAccountCreated(LocalDate.now());
        List<User> userList = Collections.singletonList(user);

        //When
        List<UserDto> mappedUserDtoList = userMapper.mapToUserDtoList(userList);

        //Then
        assertNotNull(mappedUserDtoList);
        assertEquals(1, mappedUserDtoList.size());

        assertEquals(1L, (long) mappedUserDtoList.get(0).getId());
        assertEquals("Jim", mappedUserDtoList.get(0).getName());
        assertEquals("Beam", mappedUserDtoList.get(0).getLastName());
        assertEquals("email", mappedUserDtoList.get(0).getEmail());
        assertEquals("password", mappedUserDtoList.get(0).getPassword());
        assertEquals(111222, mappedUserDtoList.get(0).getPhoneNumber());
        assertEquals(LocalDate.now(), mappedUserDtoList.get(0).getAccountCreated());
    }
}
