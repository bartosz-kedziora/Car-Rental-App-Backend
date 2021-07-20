package com.kodilla.carrental.service;

import com.kodilla.carrental.api.client.VinApiClient;
import com.kodilla.carrental.dto.VinApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VinApiService {

    private final VinApiClient vinApiClient;

    public VinApiDto decodeVin(String vin) {
        return vinApiClient.decodeVin(vin);
    }
}
