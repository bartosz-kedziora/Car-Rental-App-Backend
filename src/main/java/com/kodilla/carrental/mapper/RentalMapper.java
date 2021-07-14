package com.kodilla.carrental.mapper;

import com.kodilla.carrental.domain.Rental;
import com.kodilla.carrental.dto.RentalDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {
    public RentalDto mapToRentalDto(final Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getRentedFrom(),
                rental.getRentedTo(),
                rental.getCost(),
                rental.getCar().getId(),
                rental.getCar().getBrand(),
                rental.getCar().getModel(),
                rental.getUser().getId(),
                rental.getUser().getName(),
                rental.getUser().getLastName(),
                rental.getUser().getEmail(),
                rental.getUser().getPhoneNumber());
    }

    public List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList) {
        return rentalList.stream()
                .map(rental -> new RentalDto(
                        rental.getId(),
                        rental.getRentedFrom(),
                        rental.getRentedTo(),
                        rental.getCost(),
                        rental.getCar().getId(),
                        rental.getCar().getBrand(),
                        rental.getCar().getModel(),
                        rental.getUser().getId(),
                        rental.getUser().getName(),
                        rental.getUser().getLastName(),
                        rental.getUser().getEmail(),
                        rental.getUser().getPhoneNumber()))
                .collect(Collectors.toList());
    }
}
