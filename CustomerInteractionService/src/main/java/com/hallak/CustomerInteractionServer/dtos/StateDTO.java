package com.hallak.CustomerInteractionServer.dtos;

import lombok.Getter;

@Getter
public enum StateDTO {

    DELIVERED("Delivered successfully"),
    SHIPPED("Shipped and on the way"),
    BLOCKED("Blocked, action required"),
    SEPARATION("In separation process");

    private final String description;

    StateDTO(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}