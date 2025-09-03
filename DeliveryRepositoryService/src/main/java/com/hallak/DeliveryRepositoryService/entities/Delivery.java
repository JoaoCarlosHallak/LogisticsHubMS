package com.hallak.DeliveryRepositoryService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String vehiclePlate;

    private Long orderId;

    private String driverCpf;

    @Embedded
    private Trip trip;




}
