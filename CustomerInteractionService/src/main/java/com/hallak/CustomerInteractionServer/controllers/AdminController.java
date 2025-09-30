package com.hallak.CustomerInteractionServer.controllers;


import com.hallak.CustomerInteractionServer.services.OrderService;
import com.hallak.CustomerInteractionServer.services.UserService;
import com.hallak.shared_libraries.dtos.DeliveryToCommunicationDTO;
import com.hallak.shared_libraries.dtos.OrderDTO;
import com.hallak.shared_libraries.dtos.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/")
public class AdminController {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public AdminController(OrderService orderService, UserService userService){
        this.orderService = orderService;
        this.userService = userService;
    }


    @PostMapping(value = "dispatch/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DeliveryToCommunicationDTO> dispatchOrderById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.dispatchOrderById(id), HttpStatus.OK);
    }

    @GetMapping(value = "order/{id}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.findOrderById(id), HttpStatus.OK);
    }

    @PutMapping(value = "order/{id}/state")
    public ResponseEntity<Void> changeState(@PathVariable Long id, @RequestParam String state){
        return new ResponseEntity<>(orderService.changeState(id, state), HttpStatus.OK);
    }

    @GetMapping(value = "client/{username}")
    public ResponseEntity<UserResponseDTO> getClientByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.getClientByUsername(username), HttpStatus.OK);
    }




}
