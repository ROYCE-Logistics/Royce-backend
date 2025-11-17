package com.a1.logistics.repository;

import com.a1.logistics.entity.ExportJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExportJobRepository extends JpaRepository<ExportJob, Long> {
}
