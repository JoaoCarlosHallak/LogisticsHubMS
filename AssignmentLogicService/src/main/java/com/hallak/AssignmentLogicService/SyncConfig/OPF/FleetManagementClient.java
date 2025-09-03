package com.hallak.AssignmentLogicService.SyncConfig.OPF;

import com.hallak.shared_libraries.dtos.DriverToSyncCCDTO;
import com.hallak.shared_libraries.dtos.Situation;
import com.hallak.shared_libraries.dtos.VehicleDTO;
import com.hallak.shared_libraries.dtos.VehicleToSyncCCDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fleetManagement-service")
public interface PollFleetManagementClient {

    @GetMapping("/vehicle/params")
    List<VehicleToSyncCCDTO> findByParamsVehicle(
            @RequestParam("availability") String availability,
            @RequestParam("maintenance") String maintenance,
            @RequestParam("specification") String specification,
            @RequestParam("capacity") Double capacity);

    @GetMapping("/drive/params")
    List<DriverToSyncCCDTO> findByParamsDrive(
            @RequestParam("specification") String specification,
            @RequestParam("situation") Situation situation);



}