package com.a1.logistics.service.impl;

import com.a1.logistics.dto.driver.DriverDto;
import com.a1.logistics.dto.driver.DriverRequest;
import com.a1.logistics.entity.Driver;
import com.a1.logistics.entity.DriverStatus;
import com.a1.logistics.repository.DriverRepository;
import com.a1.logistics.repository.TrailerRepository;
import com.a1.logistics.repository.TruckRepository;
import com.a1.logistics.repository.UserRepository;
import com.a1.logistics.service.DriverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final TruckRepository truckRepository;
    private final TrailerRepository trailerRepository;

    public DriverServiceImpl(DriverRepository driverRepository,
                             UserRepository userRepository,
                             TruckRepository truckRepository,
                             TrailerRepository trailerRepository) {
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
        this.truckRepository = truckRepository;
        this.trailerRepository = trailerRepository;
    }

    @Override
    public Page<DriverDto> list(Pageable pageable) {
        return driverRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public Page<DriverDto> listActive(Pageable pageable) {
        return driverRepository.findByStatus(DriverStatus.ACTIVE, pageable).map(this::toDto);
    }

    @Override
    public DriverDto get(Long id) {
        return driverRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found"));
    }

    @Override
    public DriverDto create(DriverRequest request) {
        Driver driver = new Driver();
        driver.setUser(userRepository.findById(request.userId()).orElseThrow(() -> new IllegalArgumentException("User missing")));
        driver.setLicenseNumber(request.licenseNumber());
        driver.setTruck(request.truckId() != null ? truckRepository.findById(request.truckId()).orElse(null) : null);
        driver.setTrailer(request.trailerId() != null ? trailerRepository.findById(request.trailerId()).orElse(null) : null);
        driver.setStatus(request.status());
        driver.setSafetyScore(request.safetyScore());
        driver.setOnlineFlag(request.onlineFlag());
        return toDto(driverRepository.save(driver));
    }

    @Override
    public DriverDto update(Long id, DriverRequest request) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        driver.setLicenseNumber(request.licenseNumber());
        driver.setTruck(request.truckId() != null ? truckRepository.findById(request.truckId()).orElse(null) : null);
        driver.setTrailer(request.trailerId() != null ? trailerRepository.findById(request.trailerId()).orElse(null) : null);
        driver.setStatus(request.status());
        driver.setSafetyScore(request.safetyScore());
        driver.setOnlineFlag(request.onlineFlag());
        return toDto(driverRepository.save(driver));
    }

    @Override
    public void setOnlineStatus(Long id, boolean online) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        driver.setOnlineFlag(online);
        driverRepository.save(driver);
    }

    @Override
    public void delete(Long id) {
        driverRepository.deleteById(id);
    }

    private DriverDto toDto(Driver driver) {
        return new DriverDto(driver.getId(),
                driver.getUser() != null ? driver.getUser().getId() : null,
                driver.getUser() != null ? driver.getUser().getName() : null,
                driver.getLicenseNumber(),
                driver.getTruck() != null ? driver.getTruck().getId() : null,
                driver.getTrailer() != null ? driver.getTrailer().getId() : null,
                driver.getStatus(),
                driver.getSafetyScore(),
                driver.isOnlineFlag());
    }
}
