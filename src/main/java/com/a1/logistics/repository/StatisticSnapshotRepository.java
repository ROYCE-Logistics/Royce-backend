package com.a1.logistics.repository;

import com.a1.logistics.entity.StatisticSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticSnapshotRepository extends JpaRepository<StatisticSnapshot, Long> {
}
