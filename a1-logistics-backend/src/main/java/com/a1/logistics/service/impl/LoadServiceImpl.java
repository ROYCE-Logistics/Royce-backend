package com.a1.logistics.service.impl;

import com.a1.logistics.dto.load.LoadAssignmentRequest;
import com.a1.logistics.dto.load.LoadDto;
import com.a1.logistics.dto.load.LoadRequest;
import com.a1.logistics.dto.load.LoadStatusUpdateRequest;
import com.a1.logistics.entity.*;
import com.a1.logistics.repository.*;
import com.a1.logistics.service.LoadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LoadServiceImpl implements LoadService {

    private final LoadRepository loadRepository;
    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;
    private final LoadStatusHistoryRepository historyRepository;
    private final UserRepository userRepository;

    public LoadServiceImpl(LoadRepository loadRepository,
                           DriverRepository driverRepository,
                           TruckRepository truckRepository,
                           LoadStatusHistoryRepository historyRepository,
                           UserRepository userRepository) {
        this.loadRepository = loadRepository;
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<LoadDto> list(Pageable pageable) {
        return loadRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public Page<LoadDto> listByStatus(String status, Pageable pageable) {
        LoadStatus loadStatus = LoadStatus.valueOf(status.toUpperCase());
        return loadRepository.findByStatus(loadStatus, pageable).map(this::toDto);
    }

    @Override
    public LoadDto get(Long id) {
        return loadRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Load not found"));
    }

    @Override
    public LoadDto create(LoadRequest request) {
        Load load = new Load();
        apply(request, load);
        return toDto(loadRepository.save(load));
    }

    @Override
    public LoadDto update(Long id, LoadRequest request) {
        Load load = loadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Load not found"));
        apply(request, load);
        return toDto(loadRepository.save(load));
    }

    @Override
    public LoadDto assign(Long id, LoadAssignmentRequest request) {
        Load load = loadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Load not found"));
        load.setAssignedDriver(request.driverId() != null ? driverRepository.findById(request.driverId()).orElse(null) : null);
        load.setAssignedTruck(request.truckId() != null ? truckRepository.findById(request.truckId()).orElse(null) : null);
        return toDto(loadRepository.save(load));
    }

    @Override
    public LoadDto updateStatus(Long id, LoadStatusUpdateRequest request, String username) {
        Load load = loadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Load not found"));
        LoadStatus old = load.getStatus();
        load.setStatus(request.status());
        Load saved = loadRepository.save(load);
        LoadStatusHistory history = LoadStatusHistory.builder()
                .load(saved)
                .oldStatus(old)
                .newStatus(request.status())
                .changedAt(Instant.now())
                .changedBy(username != null ? userRepository.findByEmail(username).orElse(null) : null)
                .build();
        historyRepository.save(history);
        return toDto(saved);
    }

    @Override
    public void delete(Long id) {
        loadRepository.deleteById(id);
    }

    @Override
    public java.util.List<com.a1.logistics.dto.load.LoadStatusHistoryDto> history(Long loadId) {
        return historyRepository.findByLoadIdOrderByChangedAtAsc(loadId).stream()
                .map(h -> new com.a1.logistics.dto.load.LoadStatusHistoryDto(
                        h.getId(), h.getOldStatus(), h.getNewStatus(), h.getChangedAt(),
                        h.getChangedBy() != null ? h.getChangedBy().getId() : null))
                .toList();
    }

    private void apply(LoadRequest request, Load load) {
        load.setCustomer(request.customer());
        load.setPickupAddress(request.pickupAddress());
        load.setPickupTime(request.pickupTime());
        load.setDeliveryAddress(request.deliveryAddress());
        load.setDeliveryTime(request.deliveryTime());
        load.setStatus(request.status());
        load.setDistance(request.distance());
        load.setRate(request.rate());
        load.setAmazon(request.isAmazon());
    }

    private LoadDto toDto(Load load) {
        return new LoadDto(load.getId(), load.getCustomer(), load.getPickupAddress(), load.getPickupTime(),
                load.getDeliveryAddress(), load.getDeliveryTime(), load.getStatus(), load.getDistance(),
                load.getRate(), load.isAmazon(),
                load.getAssignedDriver() != null ? load.getAssignedDriver().getId() : null,
                load.getAssignedTruck() != null ? load.getAssignedTruck().getId() : null);
    }
}
