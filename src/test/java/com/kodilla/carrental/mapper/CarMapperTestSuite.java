package com.kodilla.carrental.mapper;

import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.domain.Status;
import com.kodilla.carrental.dto.CarDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CarMapperTestSuite {

    @Autowired
    private CarMapper carMapper;

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

    @Test
    public void mapToCarTest() {
        //Given
        CarDto carDto = testCarDto();

        //When
        Car mappedCar = carMapper.mapToCar(carDto);

        //Then
        assertEquals(1L, (long) mappedCar.getId());
        assertEquals("testVin", mappedCar.getVin());
        assertEquals("BMW", mappedCar.getBrand());
        assertEquals("M5", mappedCar.getModel());
        assertEquals(2020, mappedCar.getProductionYear());
        assertEquals("Diesel", mappedCar.getFuelType());
        assertEquals(3.5, mappedCar.getEngineCapacity(), 0.001);
        assertEquals("Sedan", mappedCar.getBodyStyle());
        assertEquals(100000, mappedCar.getMileage());
        assertEquals(100, mappedCar.getCostPerDay().intValue());
        assertEquals(Status.AVAILABLE, mappedCar.getStatus());
    }

    @Test
    public void mapToCarDtoTest() {
        //Given
        Car car = testCar();

        //When
        CarDto mappedCarDto = carMapper.mapToCarDto(car);

        //Then
        assertEquals(1L, (long) mappedCarDto.getId());
        assertEquals("testVin", mappedCarDto.getVin());
        assertEquals("BMW", mappedCarDto.getBrand());
        assertEquals("M5", mappedCarDto.getModel());
        assertEquals(2020, mappedCarDto.getProductionYear());
        assertEquals("Diesel", mappedCarDto.getFuelType());
        assertEquals(3.5, mappedCarDto.getEngineCapacity(), 0.001);
        assertEquals("Sedan", mappedCarDto.getBodyStyle());
        assertEquals(100000, mappedCarDto.getMileage());
        assertEquals(100, mappedCarDto.getCostPerDay().intValue());
        assertEquals(Status.AVAILABLE, mappedCarDto.getStatus());
    }

    @Test
    public void mapToCarDtoList() {
        //Given
        Car car = testCar();
        List<Car> carList = Collections.singletonList(car);

        //When
        List<CarDto> mappedCarDtoList = carMapper.mapToCarDtoList(carList);

        //Then
        assertNotNull(mappedCarDtoList);
        assertEquals(1, mappedCarDtoList.size());
        assertEquals(1L, (long) mappedCarDtoList.get(0).getId());
        assertEquals("testVin", mappedCarDtoList.get(0).getVin());
        assertEquals("BMW", mappedCarDtoList.get(0).getBrand());
        assertEquals("M5", mappedCarDtoList.get(0).getModel());
        assertEquals(2020, mappedCarDtoList.get(0).getProductionYear());
        assertEquals("Diesel", mappedCarDtoList.get(0).getFuelType());
        assertEquals(3.5, mappedCarDtoList.get(0).getEngineCapacity(), 0.001);
        assertEquals("Sedan", mappedCarDtoList.get(0).getBodyStyle());
        assertEquals(100000, mappedCarDtoList.get(0).getMileage());
        assertEquals(100, mappedCarDtoList.get(0).getCostPerDay().intValue());
        assertEquals(Status.AVAILABLE, mappedCarDtoList.get(0).getStatus());
    }
}
