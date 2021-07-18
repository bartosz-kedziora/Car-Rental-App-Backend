package com.kodilla.carrental.service;

import com.kodilla.carrental.api.client.HereApiClient;
import com.kodilla.carrental.dto.CarAgencyDto;
import com.kodilla.carrental.dto.GeocodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HereApiService {

    private final HereApiClient hereApiClient;

    @Autowired
    public HereApiService(HereApiClient hereApiClient) {
        this.hereApiClient = hereApiClient;
    }

    public GeocodeDto locateGeocode(String postalCode) {
        return hereApiClient.locateGeocode(postalCode);
    }

    public CarAgencyDto searchCarRentalAgencies(String latitude, String longitude) {
        return hereApiClient.searchCarRentalAgencies(latitude, longitude);
    }
}
