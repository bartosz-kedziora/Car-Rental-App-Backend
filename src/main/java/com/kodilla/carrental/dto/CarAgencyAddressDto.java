package com.kodilla.carrental.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarAgencyAddressDto {

    @JsonProperty("street")
    private String street;

    @JsonProperty("houseNumber")
    private String houseNumber;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("countryName")
    private String countryName;
}
