package com.hallak.shared_libraries.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryToCommunicationDTO {

    private String name;
    private VehicleToSyncCCDTO vehicle;
    private OrderDTO order;
    private DriverToSyncCCDTO driver;
    private TripDTO trip;



}
