package com.hallak.FleetManagementService.dtos;


import com.hallak.shared_libraries.dtos.Situation;
import com.hallak.shared_libraries.dtos.Specification;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverDTO {
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birth_date;
    private Specification specification;
    private Situation situation;


}
