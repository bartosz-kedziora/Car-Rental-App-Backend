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

import java.util.List;


@RequiredArgsConstructor
@Service
public class RentalService {

    private final RentalMapper rentalMapper;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public List<RentalDto> getRentals() {
        return rentalMapper.mapToRentalDtoList(rentalRepository.findAll());
    }

    public RentalDto getRentalById(final Long id) throws RentalNotFoundException {
        return rentalMapper.mapToRentalDto(rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new));
    }

    public RentalDto createRental(final RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException {
        User user = userRepository.findById(rentalDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Car car = carRepository.findById(rentalDto.getCarId()).orElseThrow(CarNotFoundException::new);
        car.setStatus(Status.RENTED);
        carRepository.save(car);

        Rental rental = new Rental(rentalDto.getRentedFrom(), rentalDto.getRentedTo(), user, car);
        return rentalMapper.mapToRentalDto(rentalRepository.save(rental));
    }

    public void deleteRental(final Long id) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
    }
}
