package com.kodilla.carrental.controller;

import com.kodilla.carrental.dto.CarDto;
import com.kodilla.carrental.facade.CarFacade;
import com.kodilla.carrental.exception.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cars")
public class CarController {

    private final CarFacade carFacade;

    @GetMapping("by_id/{carId}")
    public CarDto getCarById(@PathVariable Long carId) throws CarNotFoundException {
        return carFacade.getCarById(carId);
    }

    @GetMapping("getAllCars")
    public List<CarDto> getAllCars(){
        return carFacade.getAllCars();
    }

    @GetMapping("by_vin/{vin}")
    public CarDto getCarByVin(@PathVariable String vin) throws CarNotFoundException {
        return carFacade.getCarByVin(vin);
    }


    @GetMapping("/by_brand/{brand}")
    public List<CarDto> getCarByBrand(@PathVariable String brand) {
        return carFacade.getCarsByBrand(brand);
    }

    @GetMapping("/by_year/{year}")
    public List<CarDto> getCarsByProductionYear(@PathVariable int year) {
        return carFacade.getCarsByProductionYear(year);
    }

    @GetMapping("/by_bodyStyle/{bodyStyle}")
    public List<CarDto> getCarsByBodyType(@PathVariable String bodyStyle) {
        return carFacade.getCarsByBodyType(bodyStyle);
    }

    @GetMapping("/by_fuel/{fuelType}")
    public List<CarDto> getCarsByFuelType(@PathVariable String fuelType) {
        return carFacade.getCarsByFuelType(fuelType);
    }

    @GetMapping("/by_mileage/{mileage}")
    public List<CarDto> getCarsByMileage(@PathVariable int mileage) {
        return carFacade.getCarsByMileage(mileage);
    }

    @GetMapping("/by_cost/{cost}")
    public List<CarDto> getCarsByCostPerDay(@PathVariable BigDecimal cost) {
        return carFacade.getCarsByCostPerDay(cost);
    }

    @PostMapping
    public CarDto createCar(@RequestBody CarDto carDto) {
        return carFacade.saveCar(carDto);
    }

    @PutMapping
    public CarDto updateTask(@RequestBody CarDto carDto) {
        return carFacade.saveCar(carDto);
    }

    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable Long carId) {
        carFacade.deleteCar(carId);
    }
}
