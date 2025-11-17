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
public class StatisticSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatisticPeriodType periodType;

    private Instant periodStart;

    private Instant periodEnd;

    @Column(columnDefinition = "TEXT")
    private String jsonData;
}
