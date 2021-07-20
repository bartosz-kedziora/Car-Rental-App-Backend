package com.kodilla.carrental.controller;

import com.kodilla.carrental.dto.RentalDto;
import com.kodilla.carrental.facade.RentalFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RentalController.class)
public class RentalControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalFacade rentalFacade;

    private RentalDto testRentalDto() {
        return new RentalDto(
                1L,
                LocalDate.of(2021, 1, 10),
                LocalDate.of(2021, 1, 15),
                new BigDecimal(125),
                1L,
                "BMW",
                "M5",
                1L,
                "Jim",
                "Beam",
                "testMail",
                666999);
    }

    private List<RentalDto> testRentalDtoList() {
        return Collections.singletonList(testRentalDto());
    }

    @Test
    public void shouldFetchRentalById() throws Exception {
        //Given
        when(rentalFacade.getRentalById(1L)).thenReturn(testRentalDto());

        //When & Then
        mockMvc.perform(get("/v1/rentals/by_id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void shouldFetchAllRentals() throws Exception {
        //Given

        when(rentalFacade.getAllRentals()).thenReturn(testRentalDtoList());

        //When & Then
        mockMvc.perform(get("/v1/rentals")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].carBrand", is("BMW")));
    }

    @Test
    public void shouldFetchAllRentalsByUserId() throws Exception {
        //Given
        when(rentalFacade.getRentalsByUserId(1L)).thenReturn(testRentalDtoList());

        //When & Then
        mockMvc.perform(get("/v1/rentals/by_user_id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    public void shouldFetchAllRentalsByCarId() throws Exception {
        //Given
        when(rentalFacade.getRentalsByCarId(1L)).thenReturn(testRentalDtoList());

        //When & Then
        mockMvc.perform(get("/v1/rentals/by_car_id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    public void shouldDeleteRental() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/rentals/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200));
    }
}
