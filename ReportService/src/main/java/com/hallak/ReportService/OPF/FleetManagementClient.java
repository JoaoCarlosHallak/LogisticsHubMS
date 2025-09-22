package com.hallak.ReportService.OPF;

import com.hallak.shared_libraries.dtos.DriverToSyncCCDTO;
import com.hallak.shared_libraries.dtos.VehicleToSyncCCDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "fleetManagement-service")
public interface FleetManagementClient {

    @GetMapping("findByCpf/{cpf}")
    DriverToSyncCCDTO findByCpf(@PathVariable String cpf);

    @GetMapping("findByPlate/{plate}")
    VehicleToSyncCCDTO findByPlate(@PathVariable String plate);


}