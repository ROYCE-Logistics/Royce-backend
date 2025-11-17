package com.a1.logistics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Load {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customer;

    @Column(nullable = false)
    private String pickupAddress;

    private Instant pickupTime;

    @Column(nullable = false)
    private String deliveryAddress;

    private Instant deliveryTime;

    @Enumerated(EnumType.STRING)
    private LoadStatus status;

    private Double distance;

    private Double rate;

    private boolean isAmazon;

    @ManyToOne
    private Driver assignedDriver;

    @ManyToOne
    private Truck assignedTruck;
}
