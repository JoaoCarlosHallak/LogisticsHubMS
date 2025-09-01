package com.hallak.shared_libraries.dtos;

import lombok.Data;

@Data
public class VehicleToSyncCCDTO {
        private String plate;
        private String model;
        private Double capacity;
}
