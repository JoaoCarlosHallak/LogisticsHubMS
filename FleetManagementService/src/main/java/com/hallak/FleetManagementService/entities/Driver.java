package com.hallak.FleetManagementService.entities;

import com.hallak.shared_libraries.dtos.Situation;
import com.hallak.shared_libraries.dtos.Specification;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate birth_date;

    @Enumerated(EnumType.STRING)
    private Specification specification;

    @Enumerated(EnumType.STRING)
    private Situation situation;






}
