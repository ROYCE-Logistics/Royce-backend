package com.a1.logistics.service.impl;

import com.a1.logistics.dto.settlement.SettlementDto;
import com.a1.logistics.dto.settlement.SettlementRequest;
import com.a1.logistics.entity.Driver;
import com.a1.logistics.entity.Settlement;
import com.a1.logistics.entity.SettlementStatus;
import com.a1.logistics.repository.DriverRepository;
import com.a1.logistics.repository.SettlementRepository;
import com.a1.logistics.service.SettlementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SettlementServiceImpl implements SettlementService {

    private final SettlementRepository settlementRepository;
    private final DriverRepository driverRepository;

    public SettlementServiceImpl(SettlementRepository settlementRepository, DriverRepository driverRepository) {
        this.settlementRepository = settlementRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Page<SettlementDto> list(Pageable pageable) {
        return settlementRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public SettlementDto generate(SettlementRequest request) {
        Driver driver = driverRepository.findById(request.driverId()).orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        Settlement settlement = new Settlement();
        settlement.setDriver(driver);
        settlement.setPeriodStart(request.periodStart());
        settlement.setPeriodEnd(request.periodEnd());
        settlement.setTotalAmount(BigDecimal.valueOf(Math.random() * 5000));
        settlement.setStatus(SettlementStatus.GENERATED);
        return toDto(settlementRepository.save(settlement));
    }

    @Override
    public SettlementDto markPaid(Long id) {
        Settlement settlement = settlementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Settlement not found"));
        settlement.setStatus(SettlementStatus.PAID);
        return toDto(settlementRepository.save(settlement));
    }

    private SettlementDto toDto(Settlement settlement) {
        return new SettlementDto(settlement.getId(),
                settlement.getDriver() != null ? settlement.getDriver().getId() : null,
                settlement.getPeriodStart(),
                settlement.getPeriodEnd(),
                settlement.getTotalAmount(),
                settlement.getStatus());
    }
}
