package com.kodilla.carrental.repository;

import com.kodilla.carrental.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();

    Optional<Car> findById(Long id);

    Optional<Car> findByVin(String vin);

    List<Car> findAllByBrand(String brand);

    List<Car> findAllByProductionYear(int productionYear);

    List<Car> findAllByFuelType(String fuelType);

    List<Car> findAllByMileage(int mileage);

    List<Car> findAllByBodyStyle(String bodyStyle);

    List<Car> findAllByCostPerDay(BigDecimal cost);

}
