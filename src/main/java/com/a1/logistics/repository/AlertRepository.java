package com.a1.logistics.repository;

import com.a1.logistics.entity.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    Page<Alert> findByReadFlagFalse(Pageable pageable);
}
