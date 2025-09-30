package com.hallak.shared_libraries.enums;


import lombok.Getter;

@Getter
public enum Specification {
    FLAMMABLE("Flammable", "Requires fire safety measures"),
    TOXIC("Toxic", "Hazardous to health, requires protective equipment"),
    PERISHABLE("Perishable", "Requires temperature control"),
    FRAGILE("Fragile", "Handle with care to avoid damage"),
    OVERSIZED("Oversized", "Exceeds standard vehicle dimensions"),
    HIGH_VALUE("High Value", "Requires extra security during transport"),
    RADIOACTIVE("Radioactive", "Strictly regulated, requires special authorization"),
    CORROSIVE("Corrosive", "Can damage materials, needs protective containers"),
    EXPLOSIVE("Explosive", "Requires isolation and special safety measures");

    private final String label;
    private final String description;


    Specification(String label, String description) {
        this.label = label;
        this.description = description;
    }

}
