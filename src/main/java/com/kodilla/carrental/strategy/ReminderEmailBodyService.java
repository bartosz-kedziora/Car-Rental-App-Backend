package com.kodilla.carrental.strategy;

import com.kodilla.carrental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderEmailBodyService implements EmailBodyService {

    private final RentalRepository rentalRepository;

    @Override
    public String emailBodyCreate() {
        long rentalRepositorySize = rentalRepository.count();

        return ("\n Dear Car Rental Administrator." +
                "\n\t Current number of all rentals: " + rentalRepositorySize + "\n" +
                "\n Have a nice day!" +
                "\n //Car Rental service//");
    }
}
