package com.hallak.FleetManagementService.dtos;

import com.hallak.FleetManagementService.entities.Specifications;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverDTO {
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birth_date;
    private Specifications specifications;


}
