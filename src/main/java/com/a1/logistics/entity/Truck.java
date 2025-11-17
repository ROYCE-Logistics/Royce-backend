package com.a1.logistics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String plateNumber;

    @Column(nullable = false, unique = true)
    private String vin;

    @Enumerated(EnumType.STRING)
    private TruckStatus status;

    private Long mileage;

    private LocalDate lastMaintenanceDate;

    private LocalDate insuranceExpiry;

    @Column(length = 2000)
    private String notes;

    @OneToMany(mappedBy = "truck")
    private Set<Driver> drivers;
}
