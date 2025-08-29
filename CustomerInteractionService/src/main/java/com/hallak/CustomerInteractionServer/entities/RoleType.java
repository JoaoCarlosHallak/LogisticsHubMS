package com.hallak.CustomerInteractionServer.entities;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleType implements GrantedAuthority {
    ROLE_CUSTOMER("role of the customer"),
    ROLE_ADMIN("role of the dispatcher");

    private final String description;

    RoleType(String description){
        this.description = description;
    }


    @Override
    public String getAuthority() {
        return this.name();
    }
}
