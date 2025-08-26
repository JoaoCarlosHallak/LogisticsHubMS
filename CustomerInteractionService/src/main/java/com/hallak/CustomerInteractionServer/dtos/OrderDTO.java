package com.hallak.CustomerInteractionServer.dtos;

import lombok.Data;

@Data
public class OrderDTO {

    private String name;
    private String restriction;
    private double weight;
    private RouteDTO routeDTO;
    private StateDTO state;



}
