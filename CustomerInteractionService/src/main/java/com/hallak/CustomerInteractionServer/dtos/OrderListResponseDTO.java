package com.hallak.CustomerInteractionServer.dtos;

import com.hallak.CustomerInteractionServer.entities.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponseDTO {

    private String name;
    private State state;


}
