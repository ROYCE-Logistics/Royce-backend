package com.a1.logistics.service.impl;

import com.a1.logistics.dto.truck.TruckDto;
import com.a1.logistics.dto.truck.TruckRequest;
import com.a1.logistics.entity.Truck;
import com.a1.logistics.repository.TruckRepository;
import com.a1.logistics.service.TruckService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;

    public TruckServiceImpl(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public Page<TruckDto> list(Pageable pageable) {
        return truckRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public TruckDto get(Long id) {
        return truckRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Truck not found"));
    }

    @Override
    public TruckDto create(TruckRequest request) {
        Truck truck = new Truck();
        apply(request, truck);
        return toDto(truckRepository.save(truck));
    }

    @Override
    public TruckDto update(Long id, TruckRequest request) {
        Truck truck = truckRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Truck not found"));
        apply(request, truck);
        return toDto(truckRepository.save(truck));
    }

    @Override
    public void delete(Long id) {
        truckRepository.deleteById(id);
    }

    private void apply(TruckRequest request, Truck truck) {
        truck.setPlateNumber(request.plateNumber());
        truck.setVin(request.vin());
        truck.setStatus(request.status());
        truck.setMileage(request.mileage());
        truck.setLastMaintenanceDate(request.lastMaintenanceDate());
        truck.setInsuranceExpiry(request.insuranceExpiry());
        truck.setNotes(request.notes());
    }

    private TruckDto toDto(Truck truck) {
        return new TruckDto(truck.getId(), truck.getPlateNumber(), truck.getVin(), truck.getStatus(),
                truck.getMileage(), truck.getLastMaintenanceDate(), truck.getInsuranceExpiry(), truck.getNotes());
    }
}
