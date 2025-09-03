package com.hallak.CustomerInteractionServer.controllers;


import com.hallak.CustomerInteractionServer.services.OrderService;
import com.hallak.shared_libraries.dtos.DeliveryToSyncDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/")
public class AdminController {

    private OrderService orderService;


    @PostMapping(value = "dispatch/{id}")
    public ResponseEntity<DeliveryToSyncDTO> dispatchOrderById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.dispachOrderById(id), HttpStatus.OK);



    }




}
