package com.kodilla.carrental.controller;

import com.kodilla.carrental.dto.RentalDto;
import com.kodilla.carrental.exception.CarNotFoundException;
import com.kodilla.carrental.exception.RentalNotFoundException;
import com.kodilla.carrental.exception.UserNotFoundException;
import com.kodilla.carrental.facade.RentalFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rentals")
public class RentalController {

    private final RentalFacade rentalFacade;

    @GetMapping("by_id/{rentalId}")
    public RentalDto getRentalById(@PathVariable Long rentalId) throws RentalNotFoundException {
        return rentalFacade.getRentalById(rentalId);
    }

    @GetMapping("/by_user_id/{userId}")
    public List<RentalDto> getRentalsByUserId(@PathVariable Long userId) {
        return rentalFacade.getRentalsByUserId(userId);
    }

    @GetMapping("/by_car_id/{carId}")
    public List<RentalDto> getRentalsByCarId(@PathVariable Long carId) {
        return rentalFacade.getRentalsByCarId(carId);
    }

    @GetMapping
    public List<RentalDto> getAllRentals() {
        return rentalFacade.getAllRentals();
    }

    @PostMapping
    public RentalDto createRental(@RequestBody RentalDto rentalDto) throws CarNotFoundException, UserNotFoundException {
        return rentalFacade.createRental(rentalDto);
    }

    @PutMapping
    public RentalDto updateRental(@RequestBody RentalDto rentalDto) throws CarNotFoundException, UserNotFoundException,
            RentalNotFoundException {
        return rentalFacade.modifyRental(rentalDto);
    }

    @DeleteMapping("/{rentalId}")
    public void deleteRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        rentalFacade.deleteRental(rentalId);
    }
}
