package com.kodilla.carrental.strategy;

import com.kodilla.carrental.domain.Status;
import com.kodilla.carrental.repository.CarRepository;
import com.kodilla.carrental.repository.RentalRepository;
import com.kodilla.carrental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatisticsEmailBodyService implements EmailBodyService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    @Override
    public String emailBodyCreate() {
        long userRepositorySize = userRepository.count();
        long carRentedSize = carRepository.countAllByStatus(Status.RENTED);
        long carAvailableSize = carRepository.countAllByStatus(Status.AVAILABLE);
        long rentalRepositorySize = rentalRepository.count();

        return ("\n Dear Car Rental Administrator." +
                "\n\t Below You can find daily statistics considering Your database: \n" +
                "\n\t Current number of registered users: " + userRepositorySize +
                "\n\t Current number of rented cars: " + carRentedSize +
                "\n\t Current number of available cars: " + carAvailableSize +
                "\n\t Current number of all rentals: " + rentalRepositorySize + "\n" +
                "\n Have a nice day!" +
                "\n //Car Rental service//");
    }
}
