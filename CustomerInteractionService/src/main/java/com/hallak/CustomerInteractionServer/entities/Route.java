package com.hallak.CustomerInteractionServer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "origin_name")),
        @AttributeOverride(name = "cep", column = @Column(name = "origin_cep"))
    })
    private Address origin;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "destiny_name")),
        @AttributeOverride(name = "cep", column = @Column(name = "destiny_cep"))
    })
    private Address destiny;



}
