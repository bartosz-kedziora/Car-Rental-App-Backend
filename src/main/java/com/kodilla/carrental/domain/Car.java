package com.kodilla.carrental.domain;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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

    public Car(Long id, String vin, String brand, String model, int productionYear, String fuelType, double engineCapacity,
               String bodyStyle, int mileage, BigDecimal costPerDay) {
        this.id = id;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.fuelType = fuelType;
        this.engineCapacity = engineCapacity;
        this.bodyStyle = bodyStyle;
        this.mileage = mileage;
        this.costPerDay = costPerDay;
        this.status = Status.AVAILABLE;
    }
}