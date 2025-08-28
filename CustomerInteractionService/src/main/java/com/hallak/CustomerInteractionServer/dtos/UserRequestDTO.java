package com.hallak.CustomerInteractionServer.dtos;


import com.hallak.CustomerInteractionServer.entities.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String email;

    private String cpf;

    private String username;

    private String password;

    private Set<RoleType> roles = new HashSet<>();




}
