package com.kodilla.carrental.repository;

import com.kodilla.carrental.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

    List<Rental> findAll();

    Optional<Rental> findById(Long id);
}
