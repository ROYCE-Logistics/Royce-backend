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
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    private String fileName;

    private String filePath;

    @ManyToOne
    private Load relatedLoad;

    @ManyToOne
    private Driver relatedDriver;

    private Instant uploadedAt;

    @ManyToOne
    private User uploadedBy;
}
