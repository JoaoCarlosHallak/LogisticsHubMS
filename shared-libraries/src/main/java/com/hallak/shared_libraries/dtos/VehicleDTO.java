package com.hallak.shared_libraries.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    private String plate;
    private String model;
    private Double capacity;
    private Availability availability;
    private Maintenance maintenance;
    private Specification specification;
}

