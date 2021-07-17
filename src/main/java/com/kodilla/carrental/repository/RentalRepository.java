package com.kodilla.carrental.repository;

import com.kodilla.carrental.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Transactional
@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

    List<Rental> findAll();

    Optional<Rental> findById(Long id);
}
