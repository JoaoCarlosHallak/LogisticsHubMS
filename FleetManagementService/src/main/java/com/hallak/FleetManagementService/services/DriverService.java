package com.hallak.FleetManagementService.services;

import com.hallak.FleetManagementService.dtos.DriverDTO;
import com.hallak.shared_libraries.dtos.DriverToSyncCCDTO;

import java.util.List;

public interface DriverService {
    DriverDTO newDriver(DriverDTO driverDTO);
    DriverDTO findById(Long id);
    List<DriverDTO> findAll();
    String deleteById(Long id);
    DriverDTO updateDriver(DriverDTO driverDTO);
    List<DriverToSyncCCDTO> findByParams(String situation, String specification);
    Void changeSituation(String cpf, String situation);
    DriverToSyncCCDTO findByCpf(String cpf);
}