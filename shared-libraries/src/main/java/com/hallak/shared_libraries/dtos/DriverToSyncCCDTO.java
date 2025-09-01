package com.hallak.shared_libraries.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverToSyncCCDTO {
    private String name;
    private String cpf;
    private LocalDate birth_date;
}