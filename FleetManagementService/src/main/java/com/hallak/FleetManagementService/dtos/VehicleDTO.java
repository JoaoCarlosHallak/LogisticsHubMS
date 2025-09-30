package com.hallak.FleetManagementService.dtos;

import com.hallak.shared_libraries.enums.Availability;
import com.hallak.shared_libraries.enums.Maintenance;
import com.hallak.shared_libraries.enums.Specification;
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
