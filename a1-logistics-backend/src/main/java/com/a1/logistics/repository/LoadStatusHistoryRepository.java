package com.a1.logistics.repository;

import com.a1.logistics.entity.LoadStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoadStatusHistoryRepository extends JpaRepository<LoadStatusHistory, Long> {
    List<LoadStatusHistory> findByLoadIdOrderByChangedAtAsc(Long loadId);
}
