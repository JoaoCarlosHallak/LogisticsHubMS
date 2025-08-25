package com.hallak.FleetManagementService.dtos;

import com.hallak.FleetManagementService.entities.Availability;
import com.hallak.FleetManagementService.entities.Maintenance;
import com.hallak.FleetManagementService.entities.Specifications;
import lombok.Data;

@Data
public class VehicleDTO {
        private Long id;
        private String plate;
        private String model;
        private Double capacity;
        private Availability availability;
        private Maintenance maintenance;
        private Specifications specifications;
}
