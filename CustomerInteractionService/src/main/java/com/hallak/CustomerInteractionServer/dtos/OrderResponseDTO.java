package com.hallak.CustomerInteractionServer.dtos;

import com.hallak.CustomerInteractionServer.entities.Route;
import com.hallak.CustomerInteractionServer.entities.Specifications;
import com.hallak.CustomerInteractionServer.entities.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private String name;
    private Specifications specification;
    private double weight;
    private Route route;
    private State state;

}