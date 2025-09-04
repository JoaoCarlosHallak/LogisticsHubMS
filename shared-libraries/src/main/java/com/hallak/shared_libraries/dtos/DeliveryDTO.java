package com.hallak.shared_libraries.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {

    private Long id;
    private String name;
    private String vehiclePlate;
    private Long orderId;
    private String driverCpf;
    private TripDTO trip;


}
