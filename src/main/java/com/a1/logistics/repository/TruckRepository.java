package com.a1.logistics.repository;

import com.a1.logistics.entity.Truck;
import com.a1.logistics.entity.TruckStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruckRepository extends JpaRepository<Truck, Long> {
    List<Truck> findByStatus(TruckStatus status);
}
