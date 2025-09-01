package com.hallak.AssignmentLogicService;

import com.hallak.AssignmentLogicService.services.AssignmentService;
import com.hallak.AssignmentLogicService.services.DistanceMatrixService;
import jakarta.ws.rs.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {


    private final DistanceMatrixService distanceMatrixService;

    @Autowired
    public Test(DistanceMatrixService distanceMatrixService) {
        this.distanceMatrixService = distanceMatrixService;
    }

    @GetMapping
    public ResponseEntity<Object> teste(@RequestParam String o,
                                        @RequestParam String d){
        return new ResponseEntity<>(distanceMatrixService.getTravelTimeInHours(o, d), HttpStatus.OK);

    }

}
