package com.a1.logistics.service;

import com.a1.logistics.dto.truck.TruckDto;
import com.a1.logistics.dto.truck.TruckRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TruckService {
    Page<TruckDto> list(Pageable pageable);
    TruckDto get(Long id);
    TruckDto create(TruckRequest request);
    TruckDto update(Long id, TruckRequest request);
    void delete(Long id);
}
