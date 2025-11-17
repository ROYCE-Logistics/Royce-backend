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
public class LoadStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Load load;

    @Enumerated(EnumType.STRING)
    private LoadStatus oldStatus;

    @Enumerated(EnumType.STRING)
    private LoadStatus newStatus;

    private Instant changedAt;

    @ManyToOne
    private User changedBy;
}
