package com.hallak.CustomerInteractionServer.services;

import com.hallak.CustomerInteractionServer.dtos.UserRequestDTO;
import com.hallak.CustomerInteractionServer.entities.User;
import com.hallak.shared_libraries.dtos.UserResponseDTO;

public interface UserService {
    UserRequestDTO newUser(UserRequestDTO userRequestDTO);
    User authenticated();

    UserResponseDTO getClientByUsername(String username);
}
