package com.hallak.shared_libraries.enums;

public enum Situation {
    ON_ROUTE("On Route", "Driver is currently transporting cargo"),
    UNAVAILABLE("Unavailable", "Driver is not available for assignments"),
    AVAILABLE("Available", "Driver is ready for a new trip");

    private final String label;
    private final String description;


    Situation(String label, String description) {
        this.label = label;
        this.description = description;
    }

}

