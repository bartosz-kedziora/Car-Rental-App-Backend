package com.kodilla.carrental.controller;

import com.google.gson.Gson;
import com.kodilla.carrental.domain.Status;
import com.kodilla.carrental.dto.CarDto;
import com.kodilla.carrental.facade.CarFacade;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CarController.class)
public class CarControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarFacade carFacade;

    private CarDto testCarDto() {
        return new CarDto(
                1L,
                "testVin",
                "BMW",
                "M5",
                2020,
                "Diesel",
                3.5,
                "Sedan",
                100000,
                new BigDecimal(100),
                Status.AVAILABLE);
    }

    private List<CarDto> testCarDtoList() {
        return Collections.singletonList(testCarDto());
    }

    @Test
    public void shouldFetchCarById() throws Exception {

        //Given
        when(carFacade.getCarById(1L)).thenReturn(testCarDto());

        //When & Then
        mockMvc.perform(get("/v1/cars/by_id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.vin", is("testVin")))
                .andExpect(jsonPath("$.brand", is("BMW")))
                .andExpect(jsonPath("$.model", is("M5")))
                .andExpect(jsonPath("$.productionYear", is(2020)))
                .andExpect(jsonPath("$.fuelType", is("Diesel")))
                .andExpect(jsonPath("$.engineCapacity", is(3.5)))
                .andExpect(jsonPath("$.bodyStyle", is("Sedan")))
                .andExpect(jsonPath("$.mileage", is(100000)))
                .andExpect(jsonPath("$.costPerDay", is(100)))
                .andExpect(jsonPath("$.status", is("AVAILABLE")));
    }

    @Test
    public void shouldFetchCarByVin() throws Exception {

        //Given
        when(carFacade.getCarByVin("testVin")).thenReturn(testCarDto());

        //When & Then
        mockMvc.perform(get("/v1/cars/by_vin/testVin")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("vin", "testVin"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.vin", is("testVin")));
    }

    @Test
    public void shouldFetchAllCars() throws Exception {
        //Given
        when(carFacade.getAllCars()).thenReturn(testCarDtoList());

        //When & Then
        mockMvc.perform(get("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].brand", is("BMW")))
                .andExpect(jsonPath("$[0].model", is("M5")));
    }

    @Test
    public void shouldFetchAllCarsByBrand() throws Exception {
        //Given
        when(carFacade.getCarsByBrand("BMW")).thenReturn(testCarDtoList());

        //When & Then
        mockMvc.perform(get("/v1/cars/by_brand/BMW")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].brand", is("BMW")));
    }

    @Test
    public void shouldFetchAllCarsByYear() throws Exception {
        //Given
        when(carFacade.getCarsByProductionYear(2020)).thenReturn(testCarDtoList());

        //When & Then
        mockMvc.perform(get("/v1/cars/by_year/2020")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].productionYear", is(2020)));
    }

    @Test
    public void shouldFetchCarsByBodyType() throws Exception {
        //Given
        when(carFacade.getCarsByBodyStyle("Sedan")).thenReturn(testCarDtoList());

        //When & Then
        mockMvc.perform(get("/v1/cars/by_bodyType/Sedan")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].bodyStyle", is("Sedan")));
    }

    @Test
    public void shouldFetchAllCarsByFuelType() throws Exception {
        //Given
        when(carFacade.getCarsByFuelType("Diesel")).thenReturn(testCarDtoList());

        //When & Then
        mockMvc.perform(get("/v1/cars/by_fuel/Diesel")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].fuelType", is("Diesel")));
    }

    @Test
    public void shouldFetchCarsByMileage() throws Exception {
        //Given
        when(carFacade.getCarsByMileage(100000)).thenReturn(testCarDtoList());

        //When & Then
        mockMvc.perform(get("/v1/cars/by_mileage/100000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].mileage", is(100000)));
    }

    @Test
    public void shouldFetchCarsByCostPerDay() throws Exception {
        //Given
        when(carFacade.getCarsByCostPerDay(new BigDecimal(100))).thenReturn(testCarDtoList());

        //When & Then
        mockMvc.perform(get("/v1/cars/by_cost_per_day/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].costPerDay", is(100)));
    }

    @Test
    public void shouldCreateCar() throws Exception {
        //Given
        when(carFacade.saveCar(ArgumentMatchers.any(CarDto.class))).thenReturn(testCarDto());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(testCarDto());

        //When & Then
        mockMvc.perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.vin", is("testVin")))
                .andExpect(jsonPath("$.brand", is("BMW")))
                .andExpect(jsonPath("$.model", is("M5")));
    }

    @Test
    public void shouldUpdateCar() throws Exception {
        //Given
        when(carFacade.saveCar(ArgumentMatchers.any(CarDto.class))).thenReturn(testCarDto());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(testCarDto());

        //When & Then
        mockMvc.perform(put("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.bodyStyle", is("Sedan")))
                .andExpect(jsonPath("$.fuelType", is("Diesel")));
    }

    @Test
    public void shouldDeleteCar() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/cars/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200));
    }
}
