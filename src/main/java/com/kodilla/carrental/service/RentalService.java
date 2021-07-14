package com.kodilla.carrental.service;

import com.kodilla.carrental.domain.*;
import com.kodilla.carrental.dto.RentalDto;
import com.kodilla.carrental.exception.*;
import com.kodilla.carrental.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@RequiredArgsConstructor
@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(final Long id) throws RentalNotFoundException {
        return rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
    }

    public List<Rental> getRentalsByUserId(Long id) {
        List<Rental> rentalList = getAllRentals();
        return rentalList.stream()
                .filter(r -> r.getUser().getId().equals(id))
                .collect(Collectors.toList());
    }

    public Rental createRental(RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException {
        User user = userRepository.findById(rentalDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Car car = carRepository.findById(rentalDto.getCarId()).orElseThrow(CarNotFoundException::new);
        car.setStatus(Status.RENTED);

        Rental rental = new Rental(
                rentalDto.getRentedFrom(),
                rentalDto.getRentedTo(),
                user,
                car);

        return rentalRepository.save(rental);
    }

    public Rental updateRental(RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException, RentalNotFoundException {
        User user = userRepository.findById(rentalDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Car car = carRepository.findById(rentalDto.getCarId()).orElseThrow(CarNotFoundException::new);
        Rental rental = rentalRepository.findById(rentalDto.getId()).orElseThrow(RentalNotFoundException::new);

        rental.setUser(user);
        rental.setCar(car);
        rental.setRentedFrom(rentalDto.getRentedFrom());
        rental.setRentedTo(rentalDto.getRentedTo());
        updateDuration(rental);
        updateCost(rental);
        return rental;
    }

    public void updateDuration(Rental rental) {
        if (rental.getRentedTo().isAfter(rental.getRentedFrom())) {
            rental.setDuration(DAYS.between(rental.getRentedFrom(), rental.getRentedTo()));
        } else {
            rental.setDuration(0L);
        }
    }

    public void updateCost(Rental rental) {
        BigDecimal updatedCost = rental.getCar().getCostPerDay().multiply(new BigDecimal(rental.getDuration()));
        rental.setCost(updatedCost);
    }

    public void deleteRental(Long id) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);

        rental.getUser().getRentals().remove(rental);
        rental.getCar().getRentals().remove(rental);
        rental.getCar().setStatus(Status.AVAILABLE);

        rentalRepository.deleteById(id);
    }
}
