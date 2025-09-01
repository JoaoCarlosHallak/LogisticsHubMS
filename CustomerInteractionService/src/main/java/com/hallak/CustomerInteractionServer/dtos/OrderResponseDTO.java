package com.hallak.CustomerInteractionServer.dtos;

import com.hallak.shared_libraries.dtos.RouteDTO;
import com.hallak.shared_libraries.dtos.State;
import com.hallak.shared_libraries.dtos.Specification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private String name;
    private Specification specification;
    private double weight;
    private RouteDTO route;
    private State state;

}