package com.kodilla.carrental.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class RentalDto {
    private Long id;
    private LocalDate rentedFrom;
    private LocalDate rentedTo;
    private Long carId;
    private Long userId;
}
