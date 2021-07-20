package com.kodilla.carrental.controller;

import com.kodilla.carrental.dto.VinApiDto;
import com.kodilla.carrental.service.VinApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vin")
public class VinApiController {

    private final VinApiService vinApiService;

    @GetMapping("/{vin}")
    public VinApiDto decodeVin(@PathVariable String vin) {
        return vinApiService.decodeVin(vin);

    }
}
