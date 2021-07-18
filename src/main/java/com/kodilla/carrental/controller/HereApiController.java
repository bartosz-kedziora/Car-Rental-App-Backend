package com.kodilla.carrental.controller;

import com.kodilla.carrental.dto.CarAgencyDto;
import com.kodilla.carrental.dto.GeocodeDto;
import com.kodilla.carrental.service.HereApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/hereApi")
public class HereApiController {

    private final HereApiService hereApiService;

    @Autowired
    public HereApiController(HereApiService hereApiService) {
        this.hereApiService = hereApiService;
    }

    @GetMapping("/search_nearest_agencies_by_coordinates")
    public CarAgencyDto searchCarRentalAgencies(@RequestParam String latitude, @RequestParam String longitude) {
        return hereApiService.searchCarRentalAgencies(latitude, longitude);
    }

    @GetMapping("/check_coordinates_by_postal_code/{postalCode}")
    public GeocodeDto getCoordinates(@PathVariable String postalCode) {
        return hereApiService.locateGeocode(postalCode);
    }
}
