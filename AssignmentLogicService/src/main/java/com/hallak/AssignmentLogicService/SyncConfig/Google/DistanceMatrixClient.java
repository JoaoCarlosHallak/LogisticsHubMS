package com.hallak.AssignmentLogicService.SyncConfig.Google;

import com.hallak.AssignmentLogicService.dtos.DistanceMatrixDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "distanceMatrixClient", url = "https://maps.googleapis.com/maps/api/distancematrix/json")
public interface DistanceMatrixClient {

    @GetMapping
    DistanceMatrixDTO getDistanceMatrix(
        @RequestParam("origins") String origins,
        @RequestParam("destinations") String destinations,
        @RequestParam("key") String apiKey
    );


}
