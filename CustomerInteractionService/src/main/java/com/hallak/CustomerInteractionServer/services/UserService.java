package com.hallak.CustomerInteractionServer.services;

import com.hallak.CustomerInteractionServer.dtos.UserRequestDTO;
import com.hallak.CustomerInteractionServer.entities.User;

public interface UserService {
    UserRequestDTO newUser(UserRequestDTO userRequestDTO);
    User authenticated();
}
