package com.hallak.DeliveryRepositoryService.SyncConfig.OPF;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fleetManagement-service")
public interface FleetManagementClient {

    @PutMapping("/driver/{cpf}/situation")
    void changeSituation(
            @PathVariable String cpf,
            @RequestParam String situation);

    @PutMapping("/vehicle/{plate}/availability")
    void changeAvailability(
            @PathVariable String plate,
            @RequestParam String availability);







}