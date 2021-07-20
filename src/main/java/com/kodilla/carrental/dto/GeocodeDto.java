package com.kodilla.carrental.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeDto {

    @JsonProperty("items")
    private List<GeocodeResultDto> geocodeResultDtoList;
}
