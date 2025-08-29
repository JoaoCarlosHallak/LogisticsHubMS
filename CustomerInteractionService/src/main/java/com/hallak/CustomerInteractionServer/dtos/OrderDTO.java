package com.hallak.CustomerInteractionServer.dtos;

import com.hallak.CustomerInteractionServer.entities.State;
import com.hallak.shared_libraries.entities.Specifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String name;
    private Specifications specification;
    private double weight;
    private RouteDTO route;
    private State state;
    private UserResponseDTO userResponseDTO;



}
