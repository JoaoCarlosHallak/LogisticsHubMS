package com.hallak.FleetManagementService.dtos;

import com.hallak.shared_libraries.dtos.Availability;
import com.hallak.shared_libraries.dtos.Maintenance;
import com.hallak.shared_libraries.dtos.Specification;
import lombok.Data;

@Data
public class VehicleDTO {
        private Long id;
        private String plate;
        private String model;
        private Double capacity;
        private Availability availability;
        private Maintenance maintenance;
        private Specification specification;
}
