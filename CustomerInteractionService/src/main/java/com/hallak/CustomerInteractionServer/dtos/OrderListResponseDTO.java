package com.hallak.CustomerInteractionServer.dtos;

import com.hallak.shared_libraries.enums.State;
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
