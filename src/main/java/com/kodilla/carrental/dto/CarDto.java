package com.kodilla.carrental.dto;

import com.kodilla.carrental.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class CarDto
{
    private Long id;
    private String vin;
    private String brand;
    private String model;
    private int productionYear;
    private String fuelType;
    private double engineCapacity;
    private String bodyStyle;
    private int mileage;
    private BigDecimal costPerDay;
    private Status status;
}
