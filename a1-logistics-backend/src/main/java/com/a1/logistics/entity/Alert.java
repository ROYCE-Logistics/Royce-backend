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
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AlertType type;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    private AlertSeverity severity;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Truck truck;

    private Instant createdAt;

    private boolean readFlag;
}
