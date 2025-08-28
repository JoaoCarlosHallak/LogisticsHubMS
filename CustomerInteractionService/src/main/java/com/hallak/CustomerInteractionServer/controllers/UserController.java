package com.hallak.CustomerInteractionServer.controllers;

import com.hallak.CustomerInteractionServer.dtos.UserRequestDTO;
import com.hallak.CustomerInteractionServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserRequestDTO> newUser(@RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.newUser(userRequestDTO), HttpStatus.CREATED);
    }









}
