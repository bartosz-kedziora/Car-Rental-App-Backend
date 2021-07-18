package com.kodilla.carrental.service;


import com.kodilla.carrental.api.client.VinApiClient;
import com.kodilla.carrental.dto.VinApiDto;
import org.springframework.stereotype.Service;

@Service
public class VinApiService {

    private final VinApiClient vinApiClient;

    public VinApiService(VinApiClient vinApiClient) {
        this.vinApiClient = vinApiClient;
    }

    public VinApiDto decodeVin(String vin) {
        return vinApiClient.decodeVin(vin);
    }
}
