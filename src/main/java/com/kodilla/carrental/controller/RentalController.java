package com.kodilla.carrental.controller;


import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.dto.CarDto;
import com.kodilla.carrental.dto.RentalDto;
import com.kodilla.carrental.exception.CarNotFoundException;
import com.kodilla.carrental.exception.RentalNotFoundException;
import com.kodilla.carrental.exception.UserNotFoundException;
import com.kodilla.carrental.mapper.CarMapper;
import com.kodilla.carrental.mapper.RentalMapper;
import com.kodilla.carrental.service.CarService;
import com.kodilla.carrental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rentals")
public class RentalController {

    private final RentalService rentalService;

    @GetMapping("by_id/{rentalId}")
    public RentalDto getRentalById(@PathVariable Long rentalId) throws RentalNotFoundException {
        return rentalService.getRentalById(rentalId);
    }

    @GetMapping(value = "getAllRentals")
    public List<RentalDto> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @PostMapping
    public void createRental(@PathVariable LocalDate rentedFrom, @PathVariable LocalDate rentedTo,
                             @PathVariable Long userId, @PathVariable Long carId)
            throws CarNotFoundException, UserNotFoundException {
        rentalService.createRental(rentedFrom, rentedTo, userId, carId);
    }

    @DeleteMapping("/{rentalId}")
    public void closeRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        rentalService.closeRental(rentalId);

    }
}
