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
public class ExportJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String format;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private Instant createdAt;

    private Instant finishedAt;

    private String downloadLink;

    private String errorMessage;
}
