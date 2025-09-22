package com.hallak.FleetManagementService.controllers;



import com.hallak.FleetManagementService.dtos.DriverDTO;
import com.hallak.FleetManagementService.services.DriverService;
import com.hallak.shared_libraries.dtos.DriverToSyncCCDTO;
import com.hallak.shared_libraries.dtos.Situation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/driver/")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<DriverDTO> newDriver(@RequestBody DriverDTO driverDTO) {
        return new ResponseEntity<>(driverService.newDriver(driverDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DriverDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(driverService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "findByCpf/{cpf}")
    public ResponseEntity<DriverToSyncCCDTO> findByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(driverService.findByCpf(cpf), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<DriverDTO>> findAll() {
        return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<DriverDTO> updateDriver(@RequestBody DriverDTO driverDTO) {
        return new ResponseEntity<>(driverService.updateDriver(driverDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(driverService.deleteById(id), HttpStatus.OK);
    }


    @GetMapping("params")
    public ResponseEntity<List<DriverToSyncCCDTO>> findByParams(@RequestParam String specification,
                                                                @RequestParam String situation) {
        return new ResponseEntity<>(driverService.findByParams(specification, situation), HttpStatus.OK);
    }


    @PutMapping(value = "{cpf}/situation")
    public ResponseEntity<Void> changeSituation(@PathVariable String cpf, @RequestParam String situation){
        return new ResponseEntity<>(driverService.changeSituation(cpf, situation), HttpStatus.OK);
    }



}







