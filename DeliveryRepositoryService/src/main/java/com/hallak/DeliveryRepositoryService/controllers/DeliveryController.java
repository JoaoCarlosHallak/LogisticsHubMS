package com.hallak.DeliveryRepositoryService.controllers;

import com.hallak.DeliveryRepositoryService.entities.Delivery;
import com.hallak.DeliveryRepositoryService.services.DeliveryRepositoryService;
import com.hallak.shared_libraries.dtos.DeliveryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/delivery/")
public class DeliveryController {

    private final DeliveryRepositoryService deliveryRepositoryService;

    @Autowired
    public DeliveryController(DeliveryRepositoryService deliveryRepositoryService) {
        this.deliveryRepositoryService = deliveryRepositoryService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDTO>> findAll(){
        return new ResponseEntity<>(deliveryRepositoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "{deliveryId}")
    public ResponseEntity<byte[]> deliveryOrder(@PathVariable Long deliveryId){
        return new ResponseEntity<>(deliveryRepositoryService.deliveryOrder(deliveryId), HttpStatus.OK);

    }



}
