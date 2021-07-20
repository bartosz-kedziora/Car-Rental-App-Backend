package com.kodilla.carrental.service;

import com.kodilla.carrental.api.client.HereApiClient;
import com.kodilla.carrental.dto.CarAgencyDto;
import com.kodilla.carrental.dto.GeocodeDto;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HereApiService {

    private final HereApiClient hereApiClient;

    public GeocodeDto locateGeocode(String postalCode) {
        return hereApiClient.locateGeocode(postalCode);
    }

    public CarAgencyDto searchCarRentalAgencies(String latitude, String longitude) {
        return hereApiClient.searchCarRentalAgencies(latitude, longitude);
    }
}
