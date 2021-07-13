package com.kodilla.carrental.service;

import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.domain.Rental;
import com.kodilla.carrental.domain.Status;
import com.kodilla.carrental.domain.User;
import com.kodilla.carrental.dto.RentalDto;
import com.kodilla.carrental.exception.CarNotFoundException;
import com.kodilla.carrental.exception.RentalNotFoundException;
import com.kodilla.carrental.exception.UserNotFoundException;
import com.kodilla.carrental.mapper.RentalMapper;
import com.kodilla.carrental.repository.CarRepository;
import com.kodilla.carrental.repository.RentalRepository;
import com.kodilla.carrental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Service
public class RentalService {

    private final RentalMapper rentalMapper;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public Rental createRental(final LocalDate rentedFrom, final LocalDate rentedTo, final Long userId, final Long carId)
            throws UserNotFoundException, CarNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);

        car.setStatus(Status.RENTED);
        carRepository.save(car);

        Rental rental = new Rental(rentedFrom, rentedTo, user, car);
        return rentalRepository.save(rental);
    }

    public RentalDto getRentalById(Long id) throws RentalNotFoundException {
        return rentalMapper.mapToRentalDto(rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new));
    }

    public List<RentalDto> getAllRentals() {
        return rentalMapper.mapToRentalDtoList(rentalRepository.findAll());
    }

    public void closeRental(final Long id) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);

        rental.getUser().getRentals().remove(rental);
        rental.getCar().getRentals().remove(rental);
        rental.getCar().setStatus(Status.AVAILABLE);

        rentalRepository.deleteById(id);
    }
}
