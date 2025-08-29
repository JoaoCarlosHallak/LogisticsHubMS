package com.hallak.shared_libraries.entities;

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
    private Specifications specifications;
}

