package com.hallak.shared_libraries.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryToSyncDTO {

    private String name;
    private VehicleToSyncCCDTO vehicle;
    private OrderDTO orderDTO;
    private DriverToSyncCCDTO driverDTO;
    private TripDTO tripDTO;



}
