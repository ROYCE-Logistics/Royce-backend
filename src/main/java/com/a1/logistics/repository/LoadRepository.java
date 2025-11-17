package com.a1.logistics.repository;

import com.a1.logistics.entity.Load;
import com.a1.logistics.entity.LoadStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface LoadRepository extends JpaRepository<Load, Long> {
    Page<Load> findByStatus(LoadStatus status, Pageable pageable);
    Page<Load> findByCustomerContainingIgnoreCase(String customer, Pageable pageable);
    Page<Load> findByPickupTimeBetween(Instant start, Instant end, Pageable pageable);
}
