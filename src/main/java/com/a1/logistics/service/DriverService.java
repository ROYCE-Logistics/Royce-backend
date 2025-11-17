package com.a1.logistics.service;

import com.a1.logistics.dto.driver.DriverDto;
import com.a1.logistics.dto.driver.DriverRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DriverService {
    Page<DriverDto> list(Pageable pageable);
    Page<DriverDto> listActive(Pageable pageable);
    DriverDto get(Long id);
    DriverDto create(DriverRequest request);
    DriverDto update(Long id, DriverRequest request);
    void setOnlineStatus(Long id, boolean online);
    void delete(Long id);
}
