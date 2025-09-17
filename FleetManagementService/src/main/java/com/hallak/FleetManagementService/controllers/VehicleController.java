package com.hallak.FleetManagementService.controllers;

import com.hallak.FleetManagementService.dtos.VehicleDTO;
import com.hallak.FleetManagementService.services.VehicleService;
import com.hallak.shared_libraries.dtos.Availability;
import com.hallak.shared_libraries.dtos.Maintenance;
import com.hallak.shared_libraries.dtos.VehicleToSyncCCDTO;
import jakarta.persistence.EnumType;
import org.apache.commons.lang.enums.Enum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehicle/")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }



    @PostMapping
    public ResponseEntity<VehicleDTO> newVehicle(@RequestBody VehicleDTO vehicleDTO){
        return new ResponseEntity<>(vehicleService.newVehicle(vehicleDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(vehicleService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll(){
        return new ResponseEntity<>(vehicleService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO){
        return new ResponseEntity<>(vehicleService.updateVehicle(vehicleDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(vehicleService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("params")
    public ResponseEntity<List<VehicleToSyncCCDTO>> findByParams(@RequestParam String availability,
                                                                 @RequestParam String maintenance,
                                                                 @RequestParam String specification,
                                                                 @RequestParam Double capacity){
        return new ResponseEntity<>(vehicleService.findByParams(availability, specification, maintenance, capacity), HttpStatus.OK);


    }


    @PatchMapping(value = "{id}/maintenance")
    public ResponseEntity<Void> changeMaintenance(@PathVariable Long vehicleId, @RequestParam String maintenance){
        return new ResponseEntity<>(vehicleService.changeMaintenance(vehicleId, maintenance), HttpStatus.OK);
    }



    @PatchMapping(value = "{id}/available-to-running")
    public ResponseEntity<Void> changeAvailability(@PathVariable Long vehicleId, @RequestParam String availability){
        return new ResponseEntity<>(vehicleService.changeAvailability(vehicleId, availability), HttpStatus.OK);
    }




}
