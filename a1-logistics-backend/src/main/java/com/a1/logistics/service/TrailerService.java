package com.a1.logistics.service;

import com.a1.logistics.dto.trailer.TrailerDto;
import com.a1.logistics.dto.trailer.TrailerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrailerService {
    Page<TrailerDto> list(Pageable pageable);
    TrailerDto get(Long id);
    TrailerDto create(TrailerRequest request);
    TrailerDto update(Long id, TrailerRequest request);
    void delete(Long id);
}
