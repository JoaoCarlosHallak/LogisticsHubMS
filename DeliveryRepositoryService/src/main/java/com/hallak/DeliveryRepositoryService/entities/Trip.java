package com.hallak.DeliveryRepositoryService.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;


}
