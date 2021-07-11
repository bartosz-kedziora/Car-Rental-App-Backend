package com.kodilla.carrental.controller;

import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.dto.CarDto;
import com.kodilla.carrental.mapper.CarMapper;
import com.kodilla.carrental.service.CarService;
import com.kodilla.carrental.exception.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cars")
public class CarController {

    private final CarMapper carMapper;
    private final CarService carService;

    @GetMapping("by_id/{carId}")
    public CarDto getCar(@PathVariable Long carId) throws CarNotFoundException{
        return carMapper.mapToCarDto(
                carService.getCarById(carId));
    }

    @GetMapping("getAllCars")
    public List<CarDto> getAllCars() throws CarNotFoundException{
            List<Car> cars = carService.getAllCars();
            return carMapper.mapToCarDtoList(cars);
    }

    @GetMapping("by_vin/{vin}")
    public CarDto getCarByVin(@PathVariable String vin) throws CarNotFoundException {
        return carMapper.mapToCarDto(carService.getCarByVin(vin));
    }

    @GetMapping("/by_brand/{brand}")
    public List<CarDto> getCarByBrand(@PathVariable String brand) {
        return carMapper.mapToCarDtoList(carService.getCarsByBrand(brand));
    }

    @GetMapping("/by_year/{year}")
    public List<CarDto> getCarsByProductionYear(@PathVariable int year) {
        return carMapper.mapToCarDtoList(carService.getCarsByProductionYear(year));
    }

    @GetMapping("/by_fuel/{fuelType}")
    public List<CarDto> getCarsByFuelType(@PathVariable String fuelType) {
        return carMapper.mapToCarDtoList(carService.getCarsByFuelType(fuelType));
    }

    @GetMapping("/by_mileage/{mileage}")
    public List<CarDto> getCarsByMileage(@PathVariable int mileage) {
        return carMapper.mapToCarDtoList(carService.getCarsByMileage(mileage));
    }

    @GetMapping("/by_cost/{cost}")
    public List<CarDto> getCarsByCostPerDay(@PathVariable BigDecimal cost) {
        return carMapper.mapToCarDtoList(carService.getCarsByCostPerDay(cost));
    }

    @PostMapping
    public void createCar(@RequestBody CarDto carDto) {
        Car car = carMapper.mapToCar(carDto);
        carService.saveCar(car);
    }

    @PutMapping
    public CarDto updateTask(@RequestBody CarDto carDto) {
        Car car = carMapper.mapToCar(carDto);
        Car savedCar = carService.saveCar(car);
        return carMapper.mapToCarDto(savedCar);
    }

    @DeleteMapping(value = "/{carId}")
    public void deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
    }

}
