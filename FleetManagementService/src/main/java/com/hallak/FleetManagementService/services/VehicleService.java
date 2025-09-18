package com.hallak.FleetManagementService.services;

import com.hallak.FleetManagementService.dtos.VehicleDTO;
import com.hallak.shared_libraries.dtos.Availability;
import com.hallak.shared_libraries.dtos.Maintenance;
import com.hallak.shared_libraries.dtos.VehicleToSyncCCDTO;

import java.util.List;

public interface VehicleService {
    VehicleDTO newVehicle(VehicleDTO vehicleDTO);
    VehicleDTO findById(Long id);
    List<VehicleDTO> findAll();
    String deleteById(Long id);
    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);
    List<VehicleToSyncCCDTO> findByParams(String availability, String specification, String maintenance, Double capacity);
    Void changeMaintenance(String plate, String maintenance);
    Void changeAvailability(String plate, String availability);
    VehicleToSyncCCDTO findByPlate(String plate);
}

