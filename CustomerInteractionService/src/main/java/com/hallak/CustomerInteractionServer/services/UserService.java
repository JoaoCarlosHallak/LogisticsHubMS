package com.hallak.CustomerInteractionServer.services;

import com.hallak.CustomerInteractionServer.dtos.UserDTO;

public interface UserService {
    UserDTO newUser(UserDTO userDTO);
}
