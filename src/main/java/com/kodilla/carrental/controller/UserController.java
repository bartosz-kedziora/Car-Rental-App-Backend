package com.kodilla.carrental.controller;

import com.kodilla.carrental.dto.UserDto;
import com.kodilla.carrental.exception.UserNotFoundException;
import com.kodilla.carrental.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("by_id/{userId}")
    public UserDto getUserById(@PathVariable Long userId) throws UserNotFoundException {
        return userFacade.getUserById(userId);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping("/by_email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return userFacade.getUserByEmail(email);
    }

    @GetMapping("/by_phone/{number}")
    public UserDto getUserByPhone(@PathVariable int number) throws UserNotFoundException {
        return userFacade.getUserByPhoneNumber(number);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userFacade.saveUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) throws UserNotFoundException{
        return userFacade.updateUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) throws UserNotFoundException{
        userFacade.deleteUser(userId);
    }
}

