package com.kodilla.carrental.api.client;

import com.kodilla.carrental.api.config.VinApiConfig;
import com.kodilla.carrental.dto.VinApiDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class VinApiClient {

    private final RestTemplate restTemplate;
    private final VinApiConfig vinApiConfig;

    public VinApiClient(RestTemplate restTemplate, VinApiConfig vinApiConfig) {
        this.restTemplate = restTemplate;
        this.vinApiConfig = vinApiConfig;
    }

    public VinApiDto decodeVin(String vin) {
        URI uri = getVinApiUri(vin);
        return restTemplate.getForObject(uri, VinApiDto.class);
    }

    public URI getVinApiUri(String vin) {
        return UriComponentsBuilder.fromHttpUrl(vinApiConfig.getVinEndpoint() + "/" + vin)
                .queryParam("format", "json")
                .build()
                .encode()
                .toUri();
    }
}