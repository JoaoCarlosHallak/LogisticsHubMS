package com.hallak.FleetManagementService.services;

import com.hallak.FleetManagementService.dtos.DriverDTO;
import com.hallak.FleetManagementService.dtos.VehicleDTO;

import java.util.List;

public interface DriverService {
    DriverDTO newDriver(DriverDTO driverDTO);
    DriverDTO findById(Long id);
    List<DriverDTO> findAll();
    String deleteById(Long id);
    DriverDTO updateDriver(DriverDTO driverDTO);
}