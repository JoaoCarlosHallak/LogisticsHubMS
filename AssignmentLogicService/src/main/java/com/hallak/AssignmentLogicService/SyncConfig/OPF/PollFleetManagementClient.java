package com.hallak.AssignmentLogicService.SyncConfig.OPF;

import com.hallak.shared_libraries.dtos.VehicleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fleetManagement-service")
public interface PollFleetManagementClient {

    @GetMapping("/vehicle/params")
    List<VehicleDTO> findByParams(
            @RequestParam("availability") String availability,
            @RequestParam("maintenance") String maintenance,
            @RequestParam("specification") String specification,
            @RequestParam("capacity") Double capacity);
}