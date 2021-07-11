package com.kodilla.carrental.controller;

import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.dto.CarDto;
import com.kodilla.carrental.mapper.CarMapper;
import com.kodilla.carrental.service.CarService;
import com.kodilla.carrental.exception.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cars")
public class CarController {

    private final CarMapper carMapper;
    private final CarService carService;

    @GetMapping("getAllCars")
    public List<CarDto> getAllCars() throws CarNotFoundException{
            List<Car> cars = carService.getAllCars();
            return carMapper.mapToCarDtoList(cars);
    }//Todo

    @GetMapping("/{carId}")
    public CarDto getCar(@PathVariable Long carId) throws CarNotFoundException{
        return carMapper.mapToCarDto(
                    carService.getCarById(carId));
    }

    @GetMapping("by_vin/{vin}")
    public CarDto getCarByVin(@PathVariable String vin) throws CarNotFoundException {
        return carMapper.mapToCarDto(carService.getCarByVin(vin));
    }

    @GetMapping("/by_brand/{brand}")
    public List<CarDto> getCarByBrand(@PathVariable String brand) {
        return carMapper.mapToCarDtoList(carService.getCarsByBrand(brand));
    }

//    @GetMapping("/by_year/{year}")
//    public List<CarDto> getCarsByProductionYear(@PathVariable int year) {
//        return carFacade.getCarsByProductionYear(year);
//    }




    @DeleteMapping(value = "/{id}")
    public void deleteCar(@RequestParam Long carId) {
        carService.deleteCar(carId);
    }
    //tu poprawić i posprawdzać


    @RequestMapping(method = RequestMethod.POST, value = "createCar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto) {
            Car car = carMapper.mapToCar(carDto);
            carService.saveCar(car);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateCar")
    public CarDto updateTask(@RequestBody CarDto carDto) {
        Car car = carMapper.mapToCar(carDto);
        Car savedCar = carService.saveCar(car);
        return carMapper.mapToCarDto(savedCar);
    }

}
