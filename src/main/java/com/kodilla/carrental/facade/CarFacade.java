package com.kodilla.carrental.facade;

import com.kodilla.carrental.dto.CarDto;
import com.kodilla.carrental.exception.CarNotFoundException;
import com.kodilla.carrental.mapper.CarMapper;
import com.kodilla.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CarFacade {

    private final CarService carService;
    private final CarMapper carMapper;

    public CarDto saveCar(CarDto carDto) {
        return carMapper.mapToCarDto(carService.saveCar(carMapper.mapToCar(carDto)));
    }

    public CarDto getCarById(Long id) throws CarNotFoundException {
        return carMapper.mapToCarDto(carService.getCarById(id));
    }

    public CarDto getCarByVin(String vin) throws CarNotFoundException {
        return carMapper.mapToCarDto(carService.getCarByVin(vin));
    }

    public List<CarDto> getAllCars() {
        return carMapper.mapToCarDtoList(carService.getAllCars());
    }

    public List<CarDto> getCarsByBrand(String brand) {
        return carMapper.mapToCarDtoList(carService.getCarsByBrand(brand));
    }

    public List<CarDto> getCarsByProductionYear(int year) {
        return carMapper.mapToCarDtoList(carService.getCarsByProductionYear(year));
    }

    public List<CarDto> getCarsByFuelType(String fuelType) {
        return carMapper.mapToCarDtoList(carService.getCarsByFuelType(fuelType));
    }

    public List<CarDto> getCarsByBodyType(String bodyType) {
        return carMapper.mapToCarDtoList(carService.getCarsByBodyType(bodyType));
    }

    public List<CarDto> getCarsByMileage(int distance) {
        return carMapper.mapToCarDtoList(carService.getCarsByMileage(distance));
    }

    public List<CarDto> getCarsByCostPerDay(BigDecimal cost) {
        return carMapper.mapToCarDtoList(carService.getCarsByCostPerDay(cost));
    }

    public void deleteCar(Long id) {
        carService.deleteCar(id);
    }
}
