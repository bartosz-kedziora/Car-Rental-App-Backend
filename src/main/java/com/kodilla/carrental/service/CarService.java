package com.kodilla.carrental.service;

import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.exception.CarNotFoundException;
import com.kodilla.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) throws CarNotFoundException {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public Car getCarByVin(String vin) throws CarNotFoundException {
        return carRepository.findByVin(vin).orElseThrow(CarNotFoundException::new);
    }

    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findAllByBrand(brand);
    }

    public List<Car> getCarsByProductionYear(int year) {
        return carRepository.findAllByProductionYear(year);
    }

    public List<Car> getCarsByFuelType(String fuelType) {
        return carRepository.findAllByFuelType(fuelType);
    }

    public List<Car> getCarsByBodyStyle(String bodyStyle) {
        return carRepository.findAllByBodyStyle(bodyStyle);
    }

    public List<Car> getCarsByMileage(int mileage) {
        return carRepository.findAllByMileage(mileage);
    }

    public List<Car> getCarsByCostPerDay(BigDecimal cost) {
        return carRepository.findAllByCostPerDay(cost);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
