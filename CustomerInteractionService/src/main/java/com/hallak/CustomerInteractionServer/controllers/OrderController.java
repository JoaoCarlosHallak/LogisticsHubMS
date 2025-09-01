package com.hallak.CustomerInteractionServer.controllers;

import com.hallak.CustomerInteractionServer.dtos.OrderListResponseDTO;
import com.hallak.CustomerInteractionServer.dtos.OrderResponseDTO;
import com.hallak.CustomerInteractionServer.services.OrderService;
import com.hallak.shared_libraries.dtos.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order/")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping
    public ResponseEntity<OrderDTO> newOrder(@RequestBody OrderDTO orderDTO){
        return new ResponseEntity<>(orderService.newOrder(orderDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<OrderListResponseDTO>> findMyOrders(){
        return new ResponseEntity<>(orderService.findMyOrders(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping(value = "{name}")
    public ResponseEntity<OrderResponseDTO> findMyOrder(@PathVariable String name){
        return new ResponseEntity<>(orderService.findMyOrder(name), HttpStatus.CREATED);
    }








}
