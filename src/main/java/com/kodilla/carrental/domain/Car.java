package com.kodilla.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Table(name = "CARS")
@Entity(name = "cars")
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

//    @OneToMany(targetEntity = Rental.class,
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER,
//            mappedBy = "car")
//    private List<Rental> rentals = new ArrayList<>();


//    public Car(Long id, @NotNull String vin, @NotNull String brand, @NotNull String model, @NotNull int productionYear,
//               @NotNull String fuelType, @NotNull double engineCapacity, @NotNull String bodyStyle,
//               @NotNull int mileage, @NotNull BigDecimal costPerDay, Status status) {
//        this.id = id;
//        this.vin = vin;
//        this.brand = brand;
//        this.model = model;
//        this.productionYear = productionYear;
//        this.fuelType = fuelType;
//        this.engineCapacity = engineCapacity;
//        this.bodyStyle = bodyStyle;
//        this.mileage = mileage;
//        this.costPerDay = costPerDay;
//        this.status = Status.AVAILABLE;
//    }
}