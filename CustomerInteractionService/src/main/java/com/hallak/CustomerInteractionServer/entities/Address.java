package com.hallak.CustomerInteractionServer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Address {

    private String name;
    private String cep;

}
