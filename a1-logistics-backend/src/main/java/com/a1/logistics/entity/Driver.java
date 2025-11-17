package com.a1.logistics.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Trailer trailer;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    private Double safetyScore;

    private boolean onlineFlag;
}
