package com.kodilla.carrental.service;

import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.exception.CarNotFoundException;
import com.kodilla.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public List<Car> getCarsByBrand(final String brand) {
        return carRepository.findAllByBrand(brand);
    }


    public List<Car> getCarsByProductionYear(final int year) {
        return carRepository.findAllByProductionYear(year);
    }

    public Car getCarById(final Long id) throws CarNotFoundException {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public void deleteCar(final Long id) {
        carRepository.deleteById(id);
    }

    public Car saveCar(final Car car) {
        return carRepository.save(car);
    }

//    public Car saveCar(final CarDto carDto) {
//        return carRepository.save(carMapper.mapToCar(carDto));
//    }


    public List<Car> getCarsByBodyType(final String bodyType) {
        return carRepository.findAllByBodyStyle(bodyType);
    }

    public List<Car> getCarsByFuelType(final String fuelType) {
        return carRepository.findAllByFuelType(fuelType);
    }

    public List<Car> getCarsByMileage(final int mileage) {
        return carRepository.findAllByMileage(mileage);
    }

    public List<Car> getCarsByCostPerDay(final BigDecimal cost) {
        return carRepository.findAllByCostPerDay(cost);
    }

    public Car getCarByVin(final String vin) throws CarNotFoundException {
        return carRepository.findByVin(vin).orElseThrow(CarNotFoundException::new);
    }

//    public List<Car> getCarsByCostPerDayLessThan(BigDecimal cost) {
//        return carRepository.findAllByCostPerDayLessThan(cost);
//    }


//    public List<Car> getCarsByMileageLessThen(int distance) {
//        return carRepository.findAllByMileageLessThan(distance);
//    }

}