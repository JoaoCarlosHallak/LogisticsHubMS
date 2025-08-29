package com.hallak.CustomerInteractionServer.entities;


import com.hallak.shared_libraries.entities.Specifications;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specifications specification;

    @Column(nullable = false)
    private double weight;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Route route;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;








}
