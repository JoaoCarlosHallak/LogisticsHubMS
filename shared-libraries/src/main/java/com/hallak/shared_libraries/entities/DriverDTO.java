package com.hallak.shared_libraries.entities;


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
    private Specifications specifications;


}
