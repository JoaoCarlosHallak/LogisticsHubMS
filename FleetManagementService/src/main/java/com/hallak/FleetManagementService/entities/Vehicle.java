package com.hallak.FleetManagementService.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Double capacity;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    @Enumerated(EnumType.STRING)
    private Maintenance maintenance;

    @Enumerated(EnumType.STRING)
    private Specifications specifications;











}
