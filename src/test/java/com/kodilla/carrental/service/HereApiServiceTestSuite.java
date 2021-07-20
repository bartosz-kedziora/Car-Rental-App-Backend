package com.kodilla.carrental.service;

import com.kodilla.carrental.api.client.HereApiClient;
import com.kodilla.carrental.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HereApiServiceTestSuite {

    @InjectMocks
    private HereApiService hereApiService;

    @Mock
    private HereApiClient hereApiClient;

    @Test
    public void locateGeocodeTest() {
        //Given
        GeocodePositionDto geocodePositionDto = new GeocodePositionDto("-52N", "35E");
        GeocodeResultDto geocodeResultDto = new GeocodeResultDto("title", geocodePositionDto);
        List<GeocodeResultDto> geocodeResultDtoList = Collections.singletonList(geocodeResultDto);
        GeocodeDto geocodeDto = new GeocodeDto(geocodeResultDtoList);

        when(hereApiClient.locateGeocode("61-132")).thenReturn(geocodeDto);

        //When
        GeocodeDto result = hereApiService.locateGeocode("61-132");

        //Then
        assertEquals("title", result.getGeocodeResultDtoList().get(0).getTitle());
        assertEquals("-52N", result.getGeocodeResultDtoList().get(0).getPosition().getLatitude());
        assertEquals("35E", result.getGeocodeResultDtoList().get(0).getPosition().getLongitude());
    }

    @Test
    public void searchCarRentalAgenciesTestSuite() {
        //Given
        CarAgencyAddressDto carAgencyAddressDto = new CarAgencyAddressDto(
                "Ceglana",
                "4",
                "40-514",
                "Katowice",
                "Slaskie",
                "Polska");
        CarAgencyResultDto carAgencyResultDto = new CarAgencyResultDto("99Rent", carAgencyAddressDto);
        List<CarAgencyResultDto> carAgencyResultDtoList = Collections.singletonList(carAgencyResultDto);
        CarAgencyDto carAgencyDto = new CarAgencyDto(carAgencyResultDtoList);

        when(hereApiClient.searchCarRentalAgencies("19.022238", "50.242732")).thenReturn(carAgencyDto);

        //When
        CarAgencyDto result = hereApiService.searchCarRentalAgencies("19.022238", "50.242732");

        //Then
        assertEquals("Katowice", result.getAgenciesDtoList().get(0).getAddress().getCity());
        assertEquals("Ceglana", result.getAgenciesDtoList().get(0).getAddress().getStreet());
    }
}
