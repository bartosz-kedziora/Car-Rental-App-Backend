package com.kodilla.carrental.facade;

import com.kodilla.carrental.domain.Rental;
import com.kodilla.carrental.dto.CarDto;
import com.kodilla.carrental.dto.RentalDto;
import com.kodilla.carrental.exception.*;
import com.kodilla.carrental.mapper.RentalMapper;
import com.kodilla.carrental.repository.RentalRepository;
import com.kodilla.carrental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class RentalFacade {

    private final RentalService rentalService;
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public RentalDto getRentalById(Long id) throws RentalNotFoundException {
        return rentalMapper.mapToRentalDto(rentalService.getRentalById(id));
    }
    public List<RentalDto> getAllRentals() {
        return rentalMapper.mapToRentalDtoList(rentalService.getAllRentals());
    }

    public List<RentalDto> getRentalsByUserId(Long id) {
        return rentalMapper.mapToRentalDtoList(rentalService.getRentalsByUserId(id));
    }

    public List<RentalDto> getRentalsByCarId(Long id) {
        return rentalMapper.mapToRentalDtoList(rentalService.getRentalsByCarId(id));
    }

    public RentalDto createRental(RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException {
        Rental createdRental = rentalService.createRental(rentalDto);
        return rentalMapper.mapToRentalDto(createdRental);
    }

    public RentalDto modifyRental(RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException,
            RentalNotFoundException {
        Rental updateRental = rentalService.updateRental(rentalDto);
        return rentalMapper.mapToRentalDto(updateRental);
    }

    public void deleteRental(Long id) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
        rental.setRentedTo(LocalDate.now());
        rentalService.updateDuration(rental);
        rentalService.updateCost(rental);
        rentalService.deleteRental(id);
    }
}
