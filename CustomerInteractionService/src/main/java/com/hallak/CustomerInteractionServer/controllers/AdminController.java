package com.hallak.CustomerInteractionServer.controllers;


import com.hallak.CustomerInteractionServer.services.OrderService;
import com.hallak.shared_libraries.dtos.DeliveryToASyncDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/")
public class AdminController {

    private final OrderService orderService;

    @Autowired
    public AdminController(OrderService orderService){
        this.orderService = orderService;
    }


    @PostMapping(value = "dispatch/{id}")
    public ResponseEntity<DeliveryToASyncDTO> dispatchOrderById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.dispatchOrderById(id), HttpStatus.OK);



    }




}
