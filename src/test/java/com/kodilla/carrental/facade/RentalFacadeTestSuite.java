package com.kodilla.carrental.facade;

import com.kodilla.carrental.domain.Car;
import com.kodilla.carrental.domain.Rental;
import com.kodilla.carrental.domain.User;
import com.kodilla.carrental.dto.RentalDto;
import com.kodilla.carrental.exception.CarNotFoundException;
import com.kodilla.carrental.exception.RentalNotFoundException;
import com.kodilla.carrental.exception.UserNotFoundException;
import com.kodilla.carrental.mapper.RentalMapper;
import com.kodilla.carrental.repository.RentalRepository;
import com.kodilla.carrental.service.RentalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalFacadeTestSuite {

    @InjectMocks
    private RentalFacade rentalFacade;

    @Mock
    private RentalMapper rentalMapper;

    @Mock
    private RentalService rentalService;

    @Mock
    private RentalRepository rentalRepository;


    @Test
    public void getRentalByIdTest() throws RentalNotFoundException {
        //Given
        Rental rental = initRental();
        RentalDto rentalDto = initRentalDto();

        when(rentalService.getRentalById(1L)).thenReturn(rental);
        when(rentalMapper.mapToRentalDto(rental)).thenReturn(rentalDto);

        //When
        RentalDto result = rentalFacade.getRentalById(1L);

        //Then
        assertEquals(rentalDto.getId(), result.getId());
    }

    @Test
    public void getRentalsTest() {
        //Given
        Rental rental = initRental();
        RentalDto rentalDto = initRentalDto();
        List<Rental> rentalList = Collections.singletonList(rental);
        List<RentalDto> rentalDtoList = Collections.singletonList(rentalDto);

        when(rentalService.getAllRentals()).thenReturn(rentalList);
        when(rentalMapper.mapToRentalDtoList(rentalList)).thenReturn(rentalDtoList);

        //When
        List<RentalDto> resultList = rentalFacade.getAllRentals();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(r -> {
            assertEquals(r.getId(), rentalDto.getId());
            assertEquals(r.getRentedFrom(), rentalDto.getRentedFrom());
            assertEquals(r.getRentedTo(), rentalDto.getRentedTo());
            assertEquals(r.getCarBrand(), rentalDto.getCarBrand());
        });
    }

    @Test
    public void getRentalsByUserIdTest() {
        //Given
        Rental rental = initRental();
        rental.setId(1L);
        RentalDto rentalDto = initRentalDto();
        List<Rental> rentalList = Collections.singletonList(rental);
        List<RentalDto> rentalDtoList = Collections.singletonList(rentalDto);

        when(rentalService.getRentalsByUserId(1L)).thenReturn(rentalList);
        when(rentalMapper.mapToRentalDtoList(rentalList)).thenReturn(rentalDtoList);

        //When
        List<RentalDto> filteredList = rentalFacade.getRentalsByUserId(1L);

        //Then
        assertNotNull(filteredList);
        assertEquals(1, filteredList.size());

        filteredList.forEach(r -> {
            assertEquals(r.getId(), rentalDto.getId());
            assertEquals(r.getRentedFrom(), rentalDto.getRentedFrom());
            assertEquals(r.getRentedTo(), rentalDto.getRentedTo());
            assertEquals(r.getCarBrand(), rentalDto.getCarBrand());
        });
    }

    @Test
    public void createRentalTest() throws CarNotFoundException, UserNotFoundException {
        //Given
        Rental rental = initRental();
        RentalDto rentalDto = initRentalDto();

        when(rentalService.createRental(rentalDto)).thenReturn(rental);
        when(rentalMapper.mapToRentalDto(rental)).thenReturn(rentalDto);

        //When
        RentalDto result = rentalFacade.createRental(rentalDto);

        //Then
        assertEquals(rentalDto.getId(), result.getId());
        assertEquals(rentalDto.getUserName(), result.getUserName());
        assertEquals(rentalDto.getCarModel(), result.getCarModel());
    }

    @Test
    public void updateRentalTest() throws CarNotFoundException, UserNotFoundException, RentalNotFoundException {
        //Given
        Rental rental = initRental();
        RentalDto rentalDto = initRentalDto();

        when(rentalService.updateRental(rentalDto)).thenReturn(rental);
        when(rentalMapper.mapToRentalDto(rental)).thenReturn(rentalDto);

        //When
        RentalDto result = rentalFacade.modifyRental(rentalDto);

        //Then
        assertEquals(rentalDto.getId(), result.getId());
        assertEquals(rentalDto.getUserName(), result.getUserName());
        assertEquals(rentalDto.getCarModel(), result.getCarModel());
    }


    @Test
    public void deleteRentalTest() throws RentalNotFoundException {
        //Given
        Rental rental = initRental();

        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        //When
        rentalFacade.deleteRental(1L);

        //Then
        assertEquals(LocalDate.now(), rental.getRentedTo());
        verify(rentalService, times(1)).deleteRental(1L);
    }

    private Rental initRental() {
        Car car = new Car(
                1L,
                "sampleVin",
                "Audi",
                "A3",
                2015,
                "Diesel",
                3.0,
                "Saloon",
                250000,
                new BigDecimal(25));

        User user = new User(
                1L,
                "Jack",
                "Smith",
                "email",
                "password",
                123456);

        return new Rental(
                LocalDate.of(2020, 8, 20),
                LocalDate.of(2020, 8, 25),
                user,
                car);
    }

    private RentalDto initRentalDto() {
        return new RentalDto(
                1L,
                LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 10, 15),
                new BigDecimal(100),
                1L,
                "Audi",
                "A3",
                1L,
                "Jack",
                "Smith",
                "email",
                123456);
    }
}