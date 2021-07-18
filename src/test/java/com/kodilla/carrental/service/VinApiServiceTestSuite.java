package com.kodilla.carrental.service;


import com.kodilla.carrental.api.client.VinApiClient;
import com.kodilla.carrental.dto.VinApiDto;
import com.kodilla.carrental.dto.VinBodyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class VinApiServiceTestSuite {

    @InjectMocks
    private VinApiService vinApiService;

    @Mock
    private VinApiClient vinApiClient;

    @Test
    public void decodeTest() {
        //Given
        VinBodyDto vinDtoDto = new VinBodyDto(
                "Audi",
                "A3",
                "2012",
                "Gasoline",
                "Wagon");

        List<VinBodyDto > vinResultDtoList = Collections.singletonList(vinDtoDto);
        VinApiDto vinApiDto = new VinApiDto(vinResultDtoList);

        when(vinApiClient.decodeVin("WAUFEAFMXCA868546")).thenReturn(vinApiDto);

        //When
        VinApiDto result = vinApiService.decodeVin("WAUFEAFMXCA868546");

        //Then
        assertEquals("A3", result.getVinBodyDtoList().get(0).getModel());
    }
}

