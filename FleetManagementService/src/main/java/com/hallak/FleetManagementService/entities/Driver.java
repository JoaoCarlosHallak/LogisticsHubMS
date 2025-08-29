package com.hallak.FleetManagementService.entities;

import com.hallak.shared_libraries.entities.Specifications;
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

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDate birth_date;

    @Column(nullable = false)
    private Specifications specifications;






}
