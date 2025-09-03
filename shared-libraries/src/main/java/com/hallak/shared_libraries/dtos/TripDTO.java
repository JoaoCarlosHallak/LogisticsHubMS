package com.hallak.shared_libraries.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;

    public Trip(LocalDateTime departureDate){
        this.departureDate = departureDate;
    }

}
