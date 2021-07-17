package com.kodilla.carrental.facade;

import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.domain.Status;
import com.kodilla.carrental.dto.CarDto;
import com.kodilla.carrental.exception.CarNotFoundException;
import com.kodilla.carrental.mapper.CarMapper;
import com.kodilla.carrental.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarFacadeTestSuite {

    @InjectMocks
    private CarFacade carFacade;

    @Mock
    private CarService carService;

    @Mock
    private CarMapper carMapper;

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

    private CarDto testCarDto() {
        return new CarDto(
                1L,
                "testVin",
                "BMW",
                "M5",
                2020,
                "Diesel",
                3.5,
                "Sedan",
                100000,
                new BigDecimal(100),
                Status.AVAILABLE);
    }

    private List<Car> testCarList() {
        Car car = testCar();
        return Collections.singletonList(car);
    }

    private List<CarDto> testCarDtoList() {
        CarDto carDto = testCarDto();
        return Collections.singletonList(carDto);
    }

    @Test
    public void getCarByIdTest() throws CarNotFoundException {
        //Given
        Car car = testCar();
        CarDto carDto = testCarDto();

        when(carService.getCarById(1L)).thenReturn(car);
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);

        //When
        CarDto result = carFacade.getCarById(1L);

        //Then
        assertEquals(carDto.getId(), result.getId());
        assertEquals(carDto.getModel(), result.getModel());
    }

    @Test
    public void getCarByVinTest() throws CarNotFoundException {
        //Given
        Car car = testCar();
        CarDto carDto = testCarDto();

        when(carService.getCarByVin("sampleVin")).thenReturn(car);
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);

        //When
        CarDto result = carFacade.getCarByVin("sampleVin");

        //Then
        assertEquals(carDto.getId(), result.getId());
        assertEquals(carDto.getVin(), result.getVin());
    }

    @Test
    public void getCarsTest() {
        //Given
        List<Car> carList = testCarList();
        List<CarDto> carDtoList = testCarDtoList();

        when(carService.getAllCars()).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getAllCars();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getStatus(), carDtoList.get(0).getStatus());
        });
    }

    @Test
    public void getCarsByBrandTest() {
        //Given
        List<Car> carList = testCarList();
        List<CarDto> carDtoList = testCarDtoList();

        when(carService.getCarsByBrand("BMW")).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByBrand("BMW");

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getBrand(), carDtoList.get(0).getBrand());
        });
    }

    @Test
    public void getCarsByFuelTypeTest() {
        //Given
        List<Car> carList = testCarList();
        List<CarDto> carDtoList = testCarDtoList();

        when(carService.getCarsByFuelType("Diesel")).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByFuelType("Diesel");

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getFuelType(), carDtoList.get(0).getFuelType());
        });
    }

    @Test
    public void getCarsByBodyClassTest() {
        //Given
        List<Car> carList = testCarList();
        List<CarDto> carDtoList = testCarDtoList();

        when(carService.getCarsByBodyStyle("Sedan")).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByBodyStyle("Sedan");

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getBodyStyle(), carDtoList.get(0).getBodyStyle());
        });
    }

    @Test
    public void getCarsByMileageLessThenTest() {
        //Given
        List<Car> carList = testCarList();
        List<CarDto> carDtoList = testCarDtoList();

        when(carService.getCarsByMileage(260000)).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByMileage(260000);

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getMileage(), carDtoList.get(0).getMileage());
        });
    }

    @Test
    public void getCarsByCostPerDayLessThenTest() {
        //Given
        List<Car> carList = testCarList();
        List<CarDto> carDtoList = testCarDtoList();

        when(carService.getCarsByCostPerDay(new BigDecimal(32))).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByCostPerDay(new BigDecimal(32));

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getCostPerDay(), carDtoList.get(0).getCostPerDay());
        });
    }

    @Test
    public void saveCarTest() {
        //Given
        Car car = testCar();
        CarDto carDto = testCarDto();

        when(carMapper.mapToCar(any())).thenReturn(car);
        when(carMapper.mapToCarDto(any())).thenReturn(carDto);

        //When
        CarDto savedCar = carFacade.saveCar(carDto);

        //Then
        assertEquals(carDto.getId(), savedCar.getId());
        assertEquals(carDto.getBrand(), savedCar.getBrand());
        assertEquals(carDto.getCostPerDay(), savedCar.getCostPerDay());
    }

    @Test
    public void deleteCarTest() {
        //Given
        //When
        carFacade.deleteCar(2L);

        //Then
        verify(carService, times(1)).deleteCar(2L);
    }


}
