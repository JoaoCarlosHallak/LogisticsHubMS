package com.hallak.shared_libraries.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {

    private String name;

    private VehicleDTO vehicle;

    private OrderDTO orderDTO;

    private DriverDTO driverDTO;

    private Trip trip;



}
