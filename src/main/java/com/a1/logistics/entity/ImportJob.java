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
public class ImportJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String sourceFile;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @Column(length = 2000)
    private String errorMessage;

    private Instant createdAt;

    private Instant finishedAt;
}
