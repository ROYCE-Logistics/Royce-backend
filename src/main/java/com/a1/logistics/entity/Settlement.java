package com.a1.logistics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Driver driver;

    private LocalDate periodStart;

    private LocalDate periodEnd;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private SettlementStatus status;

    @OneToMany(mappedBy = "settlement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments;
}
