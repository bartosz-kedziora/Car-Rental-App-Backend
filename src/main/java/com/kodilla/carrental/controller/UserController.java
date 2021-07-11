package com.kodilla.carrental.controller;

import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.domain.User;
import com.kodilla.carrental.dto.CarDto;
import com.kodilla.carrental.dto.UserDto;
import com.kodilla.carrental.exception.CarNotFoundException;
import com.kodilla.carrental.exception.UserNotFoundException;
import com.kodilla.carrental.mapper.CarMapper;
import com.kodilla.carrental.mapper.UserMapper;
import com.kodilla.carrental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("by_id/{userId}")
    public UserDto getUserById(@PathVariable Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserById(userId));
    }

    @GetMapping("getAllUsers")
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }


    @GetMapping("/by_email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserByEmail(email));

    }

    @GetMapping("/by_phone/{number}")
    public UserDto getUserByPhone(@PathVariable int number) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserByPhoneNumber(number));
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.saveUser(user);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userService.saveUser(user);
        return userMapper.mapToUserDto(savedUser);
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}

