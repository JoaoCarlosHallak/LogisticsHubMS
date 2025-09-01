package com.hallak.shared_libraries.dtos;

import lombok.Getter;

@Getter
public enum State {

    DELIVERED("Delivered successfully"),
    SHIPPED("Shipped and on the way"),
    BLOCKED("Blocked, action required"),
    SEPARATION("In separation process");

    private final String description;

    State(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}