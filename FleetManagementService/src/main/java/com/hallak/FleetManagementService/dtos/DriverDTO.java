package com.hallak.FleetManagementService.dtos;


import com.hallak.shared_libraries.enums.Situation;
import com.hallak.shared_libraries.enums.Specification;
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
