package com.kodilla.carrental.strategy;

import com.kodilla.carrental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReminderEmailBodyService implements EmailBodyService {

    private final RentalRepository rentalRepository;

    @Autowired
    public ReminderEmailBodyService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public String emailBodyCreate() {
        long rentalRepositorySize = rentalRepository.count();

        return ("\n Dear Car Rental Administrator." +
                "\n\t Current number of all rentals: " + rentalRepositorySize + "\n" +
                "\n Have a nice day!" +
                "\n //Car Rental service//");
    }
}
