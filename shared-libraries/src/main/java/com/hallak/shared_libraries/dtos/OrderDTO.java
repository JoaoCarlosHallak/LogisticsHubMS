package com.hallak.shared_libraries.dtos;


import com.hallak.shared_libraries.enums.Specification;
import com.hallak.shared_libraries.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private String name;
    private Specification specification;
    private double weight;
    private RouteDTO route;
    private State state;
    private UserResponseDTO userResponseDTO;



}
