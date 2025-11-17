package com.a1.logistics.repository;

import com.a1.logistics.entity.Driver;
import com.a1.logistics.entity.DriverStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Page<Driver> findByStatus(DriverStatus status, Pageable pageable);
}
