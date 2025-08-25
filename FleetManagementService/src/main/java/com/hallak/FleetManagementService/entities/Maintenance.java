package com.hallak.FleetManagementService.entities;

import lombok.Getter;

@Getter
public enum Maintenance {
    LOW("Low Level", "Routine maintenance, no urgency"),
    MEDIUM("Medium Level", "Requires attention soon, moderate urgency"),
    HIGH("High Level", "Critical issue, urgent maintenance required");

    private final String label;
    private final String description;

    Maintenance(String label, String description) {
        this.label = label;
        this.description = description;
    }

}