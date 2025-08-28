package com.hallak.CustomerInteractionServer.dtos;

import com.hallak.CustomerInteractionServer.entities.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String email;
    private String cpf;
    private String username;


}
