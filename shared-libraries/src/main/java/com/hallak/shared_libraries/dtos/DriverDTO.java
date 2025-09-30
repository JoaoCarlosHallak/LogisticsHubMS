package com.hallak.shared_libraries.dtos;


import com.hallak.shared_libraries.enums.Situation;
import com.hallak.shared_libraries.enums.Specification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private String name;
    private String cpf;
    private LocalDate birth_date;
    private Specification specification;
    private Situation situation;


}
