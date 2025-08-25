package com.hallak.FleetManagementService.entities;

import lombok.Getter;

@Getter
public enum Availability {
    RUNNING("Running", "Vehicle is currently operating"),
    MAINTENANCE("In Maintenance", "Vehicle is under maintenance, not available"),
    AVAILABLE("Available", "Vehicle is ready for use");

    private final String label;
    private final String description;

    Availability(String label, String description) {
        this.label = label;
        this.description = description;
    }


}
