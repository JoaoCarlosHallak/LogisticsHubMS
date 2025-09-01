package com.hallak.shared_libraries.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String name;
    private Specification specification;
    private double weight;
    private RouteDTO route;
    private State state;
    private UserResponseDTO userResponseDTO;



}
