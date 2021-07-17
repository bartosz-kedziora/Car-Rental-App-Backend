package com.kodilla.carrental.service;

import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.exception.CarNotFoundException;
import com.kodilla.carrental.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTestSuite {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    private Car testCar() {
        return new Car(
                1L,
                "testVin",
                "BMW",
                "M5",
                2020,
                "Diesel",
                3.5,
                "Sedan",
                100000,
                new BigDecimal(100));
    }

    private List<Car> testCarList() {
        Car car1 = testCar();

        Car car2 = new Car(
                2L,
                "testVin2",
                "Audi",
                "S7",
                2019,
                "Diesel",
                3.0,
                "Sedan",
                50000,
                new BigDecimal(150));

        return Arrays.asList(car1, car2);
    }

    @Test
    public void getAllCarsTest() {
        //Given
        List<Car> carList = testCarList();
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<Car> resultList = carService.getAllCars();

        //Then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
    }

    @Test
    public void getCarByIdTest() throws CarNotFoundException {
        //Given
        Car car = testCar();
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        //When
        Car result = carService.getCarById(1L);

        //Then
        assertEquals(car.getId(), result.getId());
    }

    @Test
    public void getCarByVinTest() throws CarNotFoundException {
        //Given
        Car car = testCar();
        when(carRepository.findByVin("testVin")).thenReturn(Optional.of(car));

        //When
        Car result = carService.getCarByVin("testVin");

        //Then
        assertEquals(car.getVin(), result.getVin());
    }

    @Test
    public void getCarsByBrandTest() {
        //Given
        List<Car> carList = testCarList();
        when(carRepository.findAllByBrand("BMW")).thenReturn(carList);

        //When
        List<Car> resultList = carService.getCarsByBrand("BMW");
        //Then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals(carList.get(0).getBrand(), resultList.get(0).getBrand());
        assertEquals(carList.get(1).getBrand(), resultList.get(1).getBrand());
    }

    @Test
    public void getCarsByProductionYearTest() {
        //Given
        List<Car> carList = testCarList();
        when(carRepository.findAllByProductionYear(2020)).thenReturn(carList);

        //When
        List<Car> resultList = carService.getCarsByProductionYear(2020);
        //Then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals(carList.get(0).getBrand(), resultList.get(0).getBrand());
        assertEquals(carList.get(1).getBrand(), resultList.get(1).getBrand());
    }


    @Test
    public void getCarsByFuelTypeTest() {
        //Given
        List<Car> carList = testCarList();
        when(carRepository.findAllByFuelType("Diesel")).thenReturn(carList);

        //When
        List<Car> resultList = carService.getCarsByFuelType("Diesel");

        //Then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals(carList.get(0).getFuelType(), resultList.get(0).getFuelType());
        assertEquals(carList.get(1).getFuelType(), resultList.get(1).getFuelType());
    }

    @Test
    public void getCarsByBodyBodyStyleTest() {
        //Given
        List<Car> carList = testCarList();
        when(carRepository.findAllByBodyStyle("Sedan")).thenReturn(carList);

        //When
        List<Car> resultList = carService.getCarsByBodyStyle("Sedan");

        //Then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals(carList.get(0).getBodyStyle(), resultList.get(0).getBodyStyle());
        assertEquals(carList.get(1).getBodyStyle(),  resultList.get(1).getBodyStyle());
    }

    @Test
    public void getCarsByMileageTest() {
        //Given
        List<Car> carList = testCarList();
        when(carRepository.findAllByMileage(100000)).thenReturn(carList);

        //When
        List<Car> resultList = carService.getCarsByMileage(100000);

        //Then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals(carList.get(0).getMileage(),  resultList.get(0).getMileage());
        assertEquals(carList.get(1).getMileage(), resultList.get(1).getMileage());
    }


    @Test
    public void getCarsByCostPerDayTest() {
        //Given
        List<Car> carList = testCarList();
        when(carRepository.findAllByCostPerDay(new BigDecimal(150))).thenReturn(carList);

        //When
        List<Car> resultList = carService.getCarsByCostPerDay(new BigDecimal(150));

        //Then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals(carList.get(0).getCostPerDay(),  resultList.get(0).getCostPerDay());
        assertEquals(carList.get(1).getCostPerDay(),  resultList.get(1).getCostPerDay());
    }

    @Test
    public void saveCarTest() {
        //Given
        Car car = testCar();
        when(carRepository.save(car)).thenReturn(car);

        //When
        Car result = carService.saveCar(car);

        //Then
        assertEquals(car.getId(), result.getId());
        assertEquals(car.getVin(), result.getVin());
        assertEquals(car.getBrand(), result.getBrand());
        assertEquals(car.getModel(), result.getModel());
        assertEquals(car.getProductionYear(), result.getProductionYear());
        assertEquals(car.getFuelType(), result.getFuelType());
        assertEquals(car.getBodyStyle(), result.getBodyStyle());
        assertEquals(car.getMileage(), result.getMileage());
        assertEquals(car.getCostPerDay(), result.getCostPerDay());
    }

    @Test
    public void deleteCarTest() {
        //Given
        //When
        carService.deleteCar(2L);

        //Then
        verify(carRepository, times(1)).deleteById(2L);
    }


}
