package com.hallak.shared_libraries.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String trace;





}
