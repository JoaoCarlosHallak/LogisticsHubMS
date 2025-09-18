package com.hallak.ReportService.OPF;

import com.hallak.shared_libraries.dtos.DriverDTO;
import com.hallak.shared_libraries.dtos.DriverToSyncCCDTO;
import com.hallak.shared_libraries.dtos.VehicleDTO;
import com.hallak.shared_libraries.dtos.VehicleToSyncCCDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "fleetManagement-service")
public interface FleetManagementClient {

    @GetMapping("{cpf}")
    DriverToSyncCCDTO findByCpf(@PathVariable String cpf);

    @GetMapping("{plate}")
    VehicleToSyncCCDTO findByPlate(@PathVariable String plate);


}