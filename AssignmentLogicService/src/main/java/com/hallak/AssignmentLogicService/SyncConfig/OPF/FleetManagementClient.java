package com.hallak.AssignmentLogicService.SyncConfig.OPF;

import com.hallak.shared_libraries.dtos.DriverToSyncCCDTO;
import com.hallak.shared_libraries.dtos.VehicleToSyncCCDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "fleetManagement-service")
public interface FleetManagementClient {

    @GetMapping("/vehicle/params")
    List<VehicleToSyncCCDTO> findByParamsVehicle(
            @RequestParam("availability") String availability,
            @RequestParam("maintenance") String maintenance,
            @RequestParam("specification") String specification,
            @RequestParam("capacity") Double capacity);

    @GetMapping("/driver/params")
    List<DriverToSyncCCDTO> findByParamsDrive(
            @RequestParam("specification") String specification,
            @RequestParam("situation") String situation);

    @PutMapping("/driver/{cpf}/situation")
    void changeSituation(
            @PathVariable String cpf,
            @RequestParam String situation);

    @PutMapping("/vehicle/{plate}/maintenance")
    void changeMaintenance(
            @PathVariable String plate,
            @RequestParam String maintenance);

    @PutMapping("/vehicle/{plate}/availability")
    void changeAvailability(
            @PathVariable String plate,
            @RequestParam String availability);







}