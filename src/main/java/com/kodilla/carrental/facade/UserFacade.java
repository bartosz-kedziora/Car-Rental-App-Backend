package com.kodilla.carrental.facade;

import com.kodilla.carrental.domain.User;
import com.kodilla.carrental.dto.UserDto;
import com.kodilla.carrental.exception.UserNotFoundException;
import com.kodilla.carrental.mapper.UserMapper;
import com.kodilla.carrental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;
    public UserDto saveUser(UserDto userDto){
            User user = userMapper.mapToUser(userDto);
            user.setCreationDate(LocalDate.now());
            User savedUser = userService.saveUser(user);
            return userMapper.mapToUserDto(savedUser);
    }

    public UserDto updateUser(UserDto userDto) throws UserNotFoundException {
            User user = userMapper.mapToUser(userDto);
            user.setCreationDate(LocalDate.now());
            return userMapper.mapToUserDto(userService.saveUser(user));
    }

    public UserDto getUserById(Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserById(id));
    }

    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserByEmail(email));
    }

    public UserDto getUserByPhoneNumber(int phoneNumber) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserByPhoneNumber(phoneNumber));
    }

    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }

    public Boolean isUserAlreadyRegistered(String email) {
        return userService.isUserAlreadyRegistered(email);
    }

    public Boolean doesUserHaveNoRents(Long id) throws UserNotFoundException {
        return userService.doesUserHaveNoRents(id);
    }
}
