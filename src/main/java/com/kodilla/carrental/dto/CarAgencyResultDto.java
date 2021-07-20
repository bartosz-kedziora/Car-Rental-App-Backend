package com.kodilla.carrental.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarAgencyResultDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("address")
    private CarAgencyAddressDto address;
}
