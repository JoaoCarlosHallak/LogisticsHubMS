package com.hallak.ReportService.controllers;

import com.hallak.ReportService.services.ReportService;
import com.hallak.shared_libraries.dtos.DeliveryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping
    public ResponseEntity<byte[]> makeReportAndReturnDelivery(@RequestBody DeliveryDTO deliveryDTO){
        return new ResponseEntity<>(reportService.makeReportAndReturnDelivery(deliveryDTO), HttpStatus.CREATED);
    }





}
