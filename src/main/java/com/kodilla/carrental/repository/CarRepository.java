package com.kodilla.carrental.repository;

import com.kodilla.carrental.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @Override
    List<Car> findAll();

    Optional<Car> findById(Long id);

    Optional<Car> findByVin(String vin);

    List<Car> findAllByBrand(String brand);

    List<Car> findAllByProductionYear(int productionYear);

    List<Car> findAllByFuelType(String fuelType);

    List<Car> findAllByBodyStyle(String bodyStyle);

    List<Car> findAllByMileage(int mileage);

    List<Car> findAllByCostPerDay(BigDecimal cost);
}
