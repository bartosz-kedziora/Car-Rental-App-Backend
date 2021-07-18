package com.kodilla.carrental.controller;

import com.kodilla.carrental.dto.VinApiDto;
import com.kodilla.carrental.service.VinApiService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/vin")
public class VinController {

    private final VinApiService vinApiService;

    public VinController(VinApiService vinApiService) {
        this.vinApiService = vinApiService;
    }

    @GetMapping("/{vin}")
    public VinApiDto decodeVin(@PathVariable String vin) {
        return vinApiService.decodeVin(vin);

    }
}
