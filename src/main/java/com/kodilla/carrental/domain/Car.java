package com.kodilla.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "VIN", unique = true)
    private String vin;

    @NotNull
    @Column(name = "BRAND")
    private String brand;

    @NotNull
    @Column(name = "MODEL")
    private String model;

    @NotNull
    @Column(name = "PRODUCTION_YEAR")
    private int productionYear;

    @NotNull
    @Column(name = "FUEL_TYPE")
    private String fuelType;

    @NotNull
    @Column(name = "ENGINE_CAPACITY")
    private double engineCapacity;

    @NotNull
    @Column(name = "BODY_STYLE")
    private String bodyStyle;

    @NotNull
    @Column(name = "MILEAGE")
    private int mileage;

    @NotNull
    @Column(name = "COST_PER_DAY")
    private BigDecimal costPerDay;

    @Enumerated
    @Column(name = "STATUS")
    private Status status;

    @OneToMany(targetEntity = Rental.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "car")
    private List<Rental> rentals = new ArrayList<>();

    public Car(Long id, String vin, String brand, String model, int productionYear, int mileage, String bodyStyle, String fuelType, double engineCapacity, BigDecimal costPerDay) {
        this.id = id;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.mileage = mileage;
        this.bodyStyle = bodyStyle;
        this.fuelType = fuelType;
        this.engineCapacity = engineCapacity;
        this.costPerDay = costPerDay;
        this.status = Status.AVAILABLE;
    }

}