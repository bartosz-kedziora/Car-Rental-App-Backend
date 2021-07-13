package com.kodilla.carrental.repository;

import com.kodilla.carrental.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

    @Override
    List<Rental> findAll();

    Optional<Rental> findById(Long id);

    Rental save(Rental rental);

    void deleteById(Long id);

    @Override
    long count();
}
