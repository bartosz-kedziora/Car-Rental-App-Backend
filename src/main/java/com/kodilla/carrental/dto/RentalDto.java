package com.kodilla.carrental.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class RentalDto {
    private Long id;
    private LocalDate rentedFrom;
    private LocalDate rentedTo;
    private Long duration;
    private BigDecimal cost;
    private String carModel;
    private Long userId;
}
