package com.hallak.FleetManagementService.services;

import com.hallak.FleetManagementService.dtos.VehicleDTO;

import java.util.List;

public interface VehicleService {
    VehicleDTO newVehicle(VehicleDTO vehicleDTO);
    VehicleDTO findById(Long id);
    List<VehicleDTO> findAll();
    String deleteById(Long id);
    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);
    List<VehicleDTO> findByParams(String availability, String specification, String maintenance);
}

