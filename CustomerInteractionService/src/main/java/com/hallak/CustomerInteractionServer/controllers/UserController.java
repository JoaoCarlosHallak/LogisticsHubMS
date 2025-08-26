package com.hallak.CustomerInteractionServer.controllers;

import com.hallak.CustomerInteractionServer.dtos.UserDTO;
import com.hallak.CustomerInteractionServer.services.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDTO> newUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.newUser(userDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Ola, voce tem autorizacao", HttpStatus.OK);
    }








}
