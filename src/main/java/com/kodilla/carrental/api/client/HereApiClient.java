package com.kodilla.carrental.api.client;

import com.kodilla.carrental.api.config.HereApiConfig;
import com.kodilla.carrental.dto.CarAgencyDto;
import com.kodilla.carrental.dto.GeocodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RequiredArgsConstructor
@Component
public class HereApiClient {

    private final RestTemplate restTemplate;
    private final HereApiConfig hereApiConfig;

    public GeocodeDto locateGeocode(String postalCode) {
        URI url = getLocalizerUri(postalCode);
        return restTemplate.getForObject(url, GeocodeDto.class);
    }

    public CarAgencyDto searchCarRentalAgencies(String latitude, String longitude) {
        URI url = getAgenciesSearcherUri(latitude, longitude);
        return restTemplate.getForObject(url, CarAgencyDto.class);
    }

    private URI getLocalizerUri(String postalCode) {
        return UriComponentsBuilder.fromHttpUrl(hereApiConfig.getHereApiEndpoint() + "/geocode")
                .queryParam("apiKey", hereApiConfig.getHereApiKey())
                .queryParam("qq", "postalCode=" + postalCode)
                .queryParam("limit", 1)
                .build().encode().toUri();
    }

    private URI getAgenciesSearcherUri(String latitude, String longitudes) {
        String geoCoordinates = latitude + "," + longitudes;

        return UriComponentsBuilder.fromHttpUrl(hereApiConfig.getHereApiEndpoint() + "/browse")
                .queryParam("apiKey", hereApiConfig.getHereApiKey())
                .queryParam("at", geoCoordinates)
                .queryParam("categories", "700-7851-0117")
                .queryParam("limit", 15)
                .build().encode().toUri();
    }
}
