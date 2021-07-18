package com.kodilla.carrental.controller;

import com.kodilla.carrental.dto.VinApiDto;
import com.kodilla.carrental.dto.VinBodyDto;
import com.kodilla.carrental.service.VinApiService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VinController.class)
public class VinApiControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VinApiService vinApiService;

    @Test
    public void shouldDecodeVin() throws Exception {
        //Given
        VinBodyDto vinBodyDto = new VinBodyDto(
                "Audi",
                "A3",
                "2012",
                "Gasoline",
                "Wagon");

        List<VinBodyDto> vinResultDtoList = Collections.singletonList(vinBodyDto);
        VinApiDto vinApiDto = new VinApiDto (vinResultDtoList);

        when(vinApiService.decodeVin("JTHFF2C26B2515141")).thenReturn(vinApiDto);


        //When & Then
        mockMvc.perform(get("/v1/vin/JTHFF2C26B2515141")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("vin", "JTHFF2C26B2515141"))
                .andExpect(status().is(200));
    }
}
