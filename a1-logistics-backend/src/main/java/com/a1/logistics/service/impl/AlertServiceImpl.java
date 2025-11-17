package com.a1.logistics.service.impl;

import com.a1.logistics.dto.alert.AlertDto;
import com.a1.logistics.dto.alert.AlertRequest;
import com.a1.logistics.entity.Alert;
import com.a1.logistics.repository.AlertRepository;
import com.a1.logistics.repository.DriverRepository;
import com.a1.logistics.repository.TruckRepository;
import com.a1.logistics.service.AlertService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;

    public AlertServiceImpl(AlertRepository alertRepository,
                            DriverRepository driverRepository,
                            TruckRepository truckRepository) {
        this.alertRepository = alertRepository;
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
    }

    @Override
    public Page<AlertDto> list(Pageable pageable) {
        return alertRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public AlertDto create(AlertRequest request) {
        Alert alert = new Alert();
        alert.setType(request.type());
        alert.setSeverity(request.severity());
        alert.setMessage(request.message());
        alert.setDriver(request.driverId() != null ? driverRepository.findById(request.driverId()).orElse(null) : null);
        alert.setTruck(request.truckId() != null ? truckRepository.findById(request.truckId()).orElse(null) : null);
        alert.setCreatedAt(Instant.now());
        alert.setReadFlag(false);
        return toDto(alertRepository.save(alert));
    }

    @Override
    public AlertDto markRead(Long id) {
        Alert alert = alertRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Alert not found"));
        alert.setReadFlag(true);
        return toDto(alertRepository.save(alert));
    }

    private AlertDto toDto(Alert alert) {
        return new AlertDto(alert.getId(), alert.getType(), alert.getSeverity(), alert.getMessage(),
                alert.getDriver() != null ? alert.getDriver().getId() : null,
                alert.getTruck() != null ? alert.getTruck().getId() : null,
                alert.getCreatedAt(), alert.isReadFlag());
    }
}
