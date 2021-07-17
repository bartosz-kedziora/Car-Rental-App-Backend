package com.kodilla.carrental.controller;

import com.kodilla.carrental.dto.UserDto;
import com.kodilla.carrental.facade.UserFacade;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    private UserDto testUserDto() {
        return new UserDto(
                1L,
                "Jim",
                "Beam",
                "testEmail",
                "password",
                666999,
                LocalDate.now());
    }

    private List<UserDto> testUserDtoList() {
        return Collections.singletonList(testUserDto());
    }

    @Test
    public void shouldFetchUserById() throws Exception {
        //Given
        when(userFacade.getUserById(1L)).thenReturn(testUserDto());

        //When & Then
        mockMvc.perform(get("/v1/users/by_id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Jim")))
                .andExpect(jsonPath("$.lastName", is("Beam")))
                .andExpect(jsonPath("$.email", is("testEmail")))
                .andExpect(jsonPath("$.password", is("password")))
                .andExpect(jsonPath("$.phoneNumber", is(666999)))
                .andExpect(jsonPath("$.accountCreated", is(LocalDate.now().toString())));
    }

    @Test
    public void shouldFetchAllUsers() throws Exception {
        //Given
        when(userFacade.getAllUsers()).thenReturn(testUserDtoList());

        //When & Then
        mockMvc.perform(get("/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Jim")))
                .andExpect(jsonPath("$[0].lastName", is("Beam")));
    }

    @Test
    public void shouldFetchUserByEmail() throws Exception {
        //Given
        when(userFacade.getUserByEmail("testEmail")).thenReturn(testUserDto());

        //When & Then
        mockMvc.perform(get("/v1/users/by_email/testEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.email", is("testEmail")));
    }

    @Test
    public void shouldFetchUserByPhone() throws Exception {
        //Given
        when(userFacade.getUserByPhoneNumber(666999)).thenReturn(testUserDto());

        //When & Then
        mockMvc.perform(get("/v1/users/by_phone/666999")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.phoneNumber", is(666999)));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200));
    }


}
