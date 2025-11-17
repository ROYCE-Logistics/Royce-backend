package com.a1.logistics.service;

import com.a1.logistics.dto.alert.AlertDto;
import com.a1.logistics.dto.alert.AlertRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertService {
    Page<AlertDto> list(Pageable pageable);
    AlertDto create(AlertRequest request);
    AlertDto markRead(Long id);
}
